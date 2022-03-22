package AQS_lock;

import java.util.concurrent.Semaphore;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/14 18:20
 * Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量，可用于秒杀系统
 */
public class TestSemaphore {
    private Semaphore semaphore = new Semaphore(4,true);
    public static void main(String[] args) {
        TestSemaphore test = new TestSemaphore();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.doSomeThing();
                }
            },i+"").start();
        }

    }
    private void doSomeThing(){
        try {
            semaphore.acquire(1);
            System.out.println("线程"+Thread.currentThread().getName()+"开始执行");
            Thread.sleep(1000);
            System.out.println("线程"+Thread.currentThread().getName()+"结束执行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release(1); //释放许可证数量和获得许可证不一致，出问题
        }
    }
}
