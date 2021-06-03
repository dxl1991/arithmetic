package AQS_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dengxinlong
 * @date 2021/2/4 16:56
 * @version 1.0
 */
public class TestLock {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition(); //condition必须在lock里面操作

    private void func(int i){
        lock.lock();
        try{
            System.out.println("Acquires lock : " + i);
            Thread.sleep(2000);
            condition.await(); //阻塞线程并且释放锁
            System.out.println("Acquires lock continue : " + i); //执行这句的时候需要重新竞争锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void releaseLock(){
        lock.lock();
        try{
            System.out.println("release one lock");
            condition.signal(); //会唤醒一个线程
        }finally {
            lock.unlock();
            System.out.println("release lock over");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();
        new Thread(()->testLock.func(1)).start();
        new Thread(()->testLock.func(2)).start();
        testLock.releaseLock();
        new Thread(()->testLock.func(3)).start();
    }
}
