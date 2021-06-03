import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoujunhua
 * @create 2020/9/8 15:01
 */
public class QPSCalculator {
    private RollingNumber rollingNumber;

    public QPSCalculator() {
        this.rollingNumber = new RollingNumber();
    }


    public void pass() {
        rollingNumber.record();
    }

    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        for (Bucket bucket : rollingNumber.getBuckets()) {
            builder.append(bucket.countTotalPassed());
            builder.append(",");
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    public long getAvgQps() {
        long total = 0;
        int count = 0;
        long min = Long.MAX_VALUE;
        for (Bucket bucket : rollingNumber.getBuckets()) {
            long value = bucket.countTotalPassed();
            if (value != 0) {
                count++;
                total += value;
                if (value < min) {
                    min = value;
                }
            }
        }
        // 去掉最低的一项
        if (count > 1) {
            total -= min;
            count--;
        }
        return total / count;
    }

    private static final class RollingNumber {
        /**
         * 槽位的数量
         */
        private int sizeOfBuckets;
        /**
         * 时间片，单位毫秒
         */
        private int unitOfTimeSlice;
        /**
         * 用于判断是否可跳过锁争抢
         */
        private int timeSliceUsedToCheckIfPossibleToBypass;
        /**
         * 槽位
         */
        private Bucket[] buckets;
        /**
         * 目标槽位的位置
         */
        private volatile int targetBucketPosition;
        /**
         * 接近目标槽位最新更新时间的时间
         */
        private volatile long latestPassedTimeCloseToTargetBucket;
        /**
         * 进入下一个槽位时使用的锁
         */
        private ReentrantLock enterNextBucketLock;

        /**
         * 默认60个槽位，槽位的时间片为1000毫秒
         */
        public RollingNumber() {
            this(60, 1000);
        }

        /**
         * 初始化Bucket数量与每个Bucket的时间片等
         *
         * @param sizeOfBuckets
         * @param unitOfTimeSlice
         */
        public RollingNumber(int sizeOfBuckets, int unitOfTimeSlice) {
            this.latestPassedTimeCloseToTargetBucket = System.currentTimeMillis() - (2 * unitOfTimeSlice);
            this.targetBucketPosition = -1;
            this.sizeOfBuckets = sizeOfBuckets;
            this.unitOfTimeSlice = unitOfTimeSlice;
            this.enterNextBucketLock = new ReentrantLock();
            this.buckets = new Bucket[sizeOfBuckets];
            this.timeSliceUsedToCheckIfPossibleToBypass = 3 * unitOfTimeSlice;
            for (int i = 0; i < sizeOfBuckets; i++) {
                this.buckets[i] = new Bucket();
            }
        }


        private void record() {
            long passTime = System.currentTimeMillis();
            if (targetBucketPosition == -1) {
                targetBucketPosition = (int) (passTime / unitOfTimeSlice) % sizeOfBuckets;
            }
            Bucket currentBucket = buckets[targetBucketPosition];
            if (passTime - latestPassedTimeCloseToTargetBucket >= unitOfTimeSlice) {
                if (enterNextBucketLock.isLocked() &&
                        (passTime - latestPassedTimeCloseToTargetBucket) < timeSliceUsedToCheckIfPossibleToBypass) {
                } else {
                    try {
                        enterNextBucketLock.lock();
                        if (passTime - latestPassedTimeCloseToTargetBucket >= unitOfTimeSlice) {
                            int nextTargetBucketPosition = (int) (passTime / unitOfTimeSlice) % sizeOfBuckets;
                            Bucket nextBucket = buckets[nextTargetBucketPosition];
                            if (nextBucket.equals(currentBucket)) {
                                if (passTime - latestPassedTimeCloseToTargetBucket >= unitOfTimeSlice) {
                                    latestPassedTimeCloseToTargetBucket = passTime;
                                }
                            } else {
                                nextBucket.reset(passTime);
                                targetBucketPosition = nextTargetBucketPosition;
                                latestPassedTimeCloseToTargetBucket = passTime;
                            }
                            nextBucket.pass();
                            return;
                        } else {
                            currentBucket = buckets[targetBucketPosition];
                        }
                    } finally {
                        enterNextBucketLock.unlock();
                    }
                }
            }
            currentBucket.pass();
        }

        public Bucket[] getBuckets() {
            return buckets;
        }
    }


    private static class Bucket implements Serializable {

        private static final long serialVersionUID = -9085720164508215774L;

        private Long latestPassedTime;

        private LongAdder longAdder;

        public Bucket() {
            this.latestPassedTime = System.currentTimeMillis();
            this.longAdder = new LongAdder();
        }


        public void pass() {
            longAdder.add(1);
        }

        public long countTotalPassed() {
            return longAdder.sum();
        }

        public long getLatestPassedTime() {
            return latestPassedTime;
        }

        public void reset(long latestPassedTime) {
            this.longAdder.reset();
            this.latestPassedTime = latestPassedTime;
        }
    }


    public static void main(String[] args) {
        try {
            final QPSCalculator qpsCalculator = new QPSCalculator();
            int threadNum = 4;
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            List<Thread> threadList = new ArrayList<Thread>();
            for (int i = 0; i < threadNum; i++) {
                threadList.add(new Thread() {
                    public void run() {
                        for (int i = 0; i < 50000000; i++) {
                            qpsCalculator.pass();
                        }
                        countDownLatch.countDown();
                    }
                });
            }

            long startTime = System.currentTimeMillis();
            for (Thread thread : threadList) {
                thread.start();
            }
            countDownLatch.await();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("totalMilliseconds:  " + totalTime + ", status:" + qpsCalculator.getStatus() + ",qps:" +
                    qpsCalculator.getAvgQps());
            TimeUnit.SECONDS.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
