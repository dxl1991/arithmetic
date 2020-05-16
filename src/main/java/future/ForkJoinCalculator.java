package future;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Author dengxinlong
 * @Date 2020/5/9 17:35
 * @slogan CODE IS TRUTH
 * ForkJoinPool：特点是少量线程完成大量任务，一般用于非阻塞的，能快速处理的业务，或阻塞时延比较少的
 * 每个线程有自己的队列，但线程空闲时候，会去别的队列窃取任务
 * 主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数，例如 quick sort 等
 */
public class ForkJoinCalculator {
    private ForkJoinPool pool;

    ForkJoinCalculator() {
        //        pool = ForkJoinPool.commonPool(); //使用公用线程池
        pool = new ForkJoinPool();
    }

    private class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        protected Long compute() {
            if (from - to < 10) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                int middle = (from + to) / 2;
                SumTask leftTask = new SumTask(numbers, from, middle);
                SumTask rightTask = new SumTask(numbers, middle + 1, to);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }

    public long sumUp(long[] numbers) {
        long result = pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        pool.shutdown();
        return result;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Instant start = Instant.now();
        //        long result = new ForkJoinCalculator().sumUp(numbers); //15ms
        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum); //68ms
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result); // 打印结果50000005000000
    }
}
