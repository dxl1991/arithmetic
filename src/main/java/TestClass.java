import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    private static TestA a = new TestA("aaaaaaaaa");
    private TestA b = new TestA("bbbbbbbbb");

    TestClass() {
        System.out.println("cccccccc");
    }

    public static void main(String[] args) {
        //        new TestClass();
        //        int count = 1000;
        //        int max = 100000;
        //        int[] temp = new int[count];
        //        Random random = new Random();
        //        for(int i=0;i<count;i++){
        //            temp[i] = random.nextInt(max);
        //        }
        //        //        quik_sort(temp);
        //        //        heapSort(temp);
        //        //        int[] temp2 = topK3(temp, 4);
        //        RadixSort.radixSort(temp,max);
        //        for (int i : temp) {
        //            System.out.print(i + ",");
        //        }
        //        Executors.newCachedThreadPool();
//        System.out.println(Integer.toBinaryString(-3333));
//        writeVarInt32(-3333);
        //        new TestClass().testWait();
//        AtomicInteger conv = new AtomicInteger(Integer.MAX_VALUE);
//        System.out.println(conv.incrementAndGet());
//        System.out.println(conv.incrementAndGet());
//        testTreeMap();
//        testConcurrentHashMap();
//        System.out.println("邓新龙邓新龙邓新龙邓新龙".length());
//        sort();
//        testTreeMap();
//        setStatus(1,true,1);
        String a = "dddddd1";
        String b = "100";
        Integer c = 100;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }

    /**
     * 设置某一位的值为0或者1
     * @return
     */
    public static void setStatus(int status ,boolean flag, int index) {
        if (flag) {
            status = status | (0x1 << index);
        } else {
            status = status & (~(0x1 << index));
        }
        System.out.println("status = "+status);
    }
    /**
     * 中文、英文、数字、下划线、[]、-
     * @param username
     * @return
     */
    public static boolean checkUserName(String username) {
        String regExp = "^[\\u4E00-\\u9FA5A-Za-z0-9_\\-\\[\\]]+$";
        if (username.matches(regExp)) {
            return true;
        }
        return false;
    }
    public static void sort(){
        List<Test> testList = new ArrayList<>();
        for(int i=0;i<6;i++){
            testList.add(new Test(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(testList);
        for(Test test : testList){
            System.out.println(test);
        }
    }
    static class Test implements Comparable<Test>{
        int i;
        long time;
        Test(int i){
            this.i = i;
            this.time = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "Test{" + "i=" + i + ", time=" + time + '}';
        }

        @Override
        public int compareTo(Test o) {
            return time >= o.time ? -1 : 1;
        }
    }

    public static void testConcurrentHashMap(){
        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
        for(int i=0;i<10000;i++){
            map.put(i,""+i);
        }
        System.out.println(map.size());
        for(String s : map.values()){
            map.remove(Integer.valueOf(s));
        }
        System.out.println(map.size());
    }

    public static void testTreeMap(){
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(3,"3");
        map.put(4,"4");
        map.put(2,"2");
        map.put(1,"1");
        map.put(6,"6");
        map.put(9,"9");
        Collection<String> temp = map.subMap(9,map.lastKey()).values();
        for(String s : temp){
            System.out.println(s);
        }
    }

    public static void writeVarInt32(int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                byte b = (byte) value;
                System.out.println(Integer.toBinaryString(value));
                return;
            } else {
                byte b = (byte) ((value & 0x7F) | 0x80);
                System.out.println(Integer.toBinaryString((value & 0x7F) | 0x80));
                value >>>= 7;
                //        new TestClass().testWait();
                //        long updateInterval = TimeUnit.SECONDS.toMillis(30);
                //        System.out.println(updateInterval);
            }
        }
    }

    //多重循环，才能用lable
    private static void testLable(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        listLoop:
        for(int i : list){
            if(i % 2 == 0){
                continue;
            }
            for(int j : list){
                if(j == 4){
                    continue listLoop;
                }
                System.out.println(i + "," + j);
            }
        }
    }

    private void testWait() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            System.out.println("线程池执行1");
        });
        executorService.submit(() -> {
            System.out.println("线程池执行2");
        });
        new Thread(() -> {
            while (true) {
            }
        }, "thread-1").start();
        new Thread(() -> {
            LockSupport.park();
        }, "thread-2").start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Object o = new Object();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    private void testDeadLock() {
        new Thread(() -> {
            lock1.lock();
            try {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
                lock2.unlock();
            } finally {
                lock1.unlock();
            }

        }, "thread-1").start();
        new Thread(() -> {
            lock2.lock();
            try {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
                lock1.unlock();
            } finally {
                lock2.unlock();
            }

        }, "thread-2").start();
    }

    private void testLock() {
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (o) {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (o) {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-2").start();
    }

    private static void testCPU50Per() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long time_start;
                int fulltime = 100;
                int runtime = 50;

                while (true) {
                    time_start = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - time_start) < runtime) {
                    }
                    try {
                        Thread.sleep(fulltime - runtime);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }).start();
        }
    }
}
