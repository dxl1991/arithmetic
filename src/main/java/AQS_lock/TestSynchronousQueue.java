package AQS_lock;

import java.util.concurrent.SynchronousQueue;

/**
 * 插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素。
 * 同样，如果线程尝试获取元素并且当前不存在任何元素，则该线程将被阻塞，直到线程将元素插入队列。
 * SynchronousQueue的内部实现了两个类
 *    1、一个是TransferStack类，使用LIFO顺序存储元素，这个类用于非公平模式（先阻塞的后被唤醒）
 *    2、一个类是TransferQueue，使用FIFI顺序存储元素，这个类用于公平模式（先阻塞的先被唤醒）
 * 队列内部不会存储元素，所以尽量避免使用add,offer此类立即返回的方法，除非有特殊需求
 *
 */
public class TestSynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                Thread.sleep(1000);
                queue.put("hello");//put方法一定会阻塞线程，等别的线程取出元素被唤醒
                System.out.println("hello程被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println(queue.poll()); //唤醒第一个线程
                Thread.sleep(1000);
                System.out.println(queue.take()); //唤醒第二个线程
                System.out.println(queue.poll());//poll方法，没有元素的时候不会阻塞，返回null
                System.out.println(queue.take());//take方法，没有元素的时候会阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.offer("被放弃"); //offer方法不会阻塞线程，但是如果没有线程等待接收数据，这个元素就直接丢弃了
        System.out.println("主线程没有阻塞");
        queue.put("dxl");//put方法一定会阻塞线程，等别的线程取出元素被唤醒
        System.out.println("主线程被唤醒");
        queue.offer("唤醒接收线程"); //offer方法不会阻塞线程，但是可以唤醒一个等待线程
    }
}
