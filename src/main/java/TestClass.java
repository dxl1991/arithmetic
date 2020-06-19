import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
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
        testTreeMap();
    }

    public static void testTreeMap(){
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(3,"3");
        map.put(4,"4");
        map.put(2,"2");
        map.put(1,"1");
        map.put(6,"6");
        map.put(9,"9");
        Collection<String> temp = map.subMap(2,map.lastKey()+1).values();
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
