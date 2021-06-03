package AQS_lock;

/**
 * @Author dengxinlong
 * @Date 2020/5/12 21:33
 * @slogan CODE IS TRUTH
 * sleep()方法并不会释放锁，wait()会释放锁
 * 都会释放cpu
 * wait()必须在synchronize里执行
 */
public class TestSleepAndWait {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("线程拿到锁");
                    Thread.sleep(3000);
                    System.out.println("线程休息完毕");
                    o.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程实现完毕");
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("主线程休息完毕");
        synchronized (o) {
            System.out.println("主线程获得锁");
            o.notify();
        }
    }
}
