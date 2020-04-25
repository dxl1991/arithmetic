package AQS_lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/20 18:10
 */
public class TestLockSupport {

    public static void main(String[] args) {
        Thread mainT = Thread.currentThread();
        System.out.println("begin.....");
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(mainT); //唤醒线程
        });
        thread.start();
        LockSupport.park(); //阻塞当前线程
        System.out.println(Thread.interrupted());
        System.out.println("end.......");
    }
}
