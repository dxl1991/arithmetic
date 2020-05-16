package AQS_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author dengxinlong
 * @Date 2020/5/8 15:41
 * @slogan CODE IS TRUTH
 *
 *  jstack用于生成java虚拟机当前时刻的线程快照
 *  jstack -l pid > D:\jstatck.txt  把进程线程信息写入jstatck.txt文件
 * （查找deadlock关键字）可以看到线程获取了哪些资源，正在等待哪些资源，从而判断哪两个线程死锁。
 *  写代码的时候给线程命名，这样就好找问题
 *  分析工具：https://www.ibm.com/support/pages/ibm-thread-and-monitor-dump-analyzer-java-tmda
 */
public class TestDeadLock {

    public static void main(String[] args) {
        new TestDeadLock().testDeadLock();
    }

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
}
