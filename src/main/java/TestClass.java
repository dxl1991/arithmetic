import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        new TestClass().testWait();
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
