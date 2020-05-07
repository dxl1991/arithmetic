package AQS_lock;

import java.util.concurrent.LinkedTransferQueue;

/**
 * （1）LinkedTransferQueue可以看作LinkedBlockingQueue、SynchronousQueue（公平模式）、ConcurrentLinkedQueue三者的集合体；
 * （2）LinkedTransferQueue的实现方式是使用一种叫做双重队列的数据结构；
 * （3）不管是取元素还是放元素都会入队；
 * （4）先尝试跟头节点比较，如果二者模式不一样，就匹配它们，组成CP，然后返回对方的值；
 * （5）如果二者模式一样，就入队，并自旋或阻塞等待被唤醒；
 * （6）至于是否入队及阻塞有四种模式，NOW、ASYNC、SYNC、TIMED；
 * （7）LinkedTransferQueue全程都没有使用synchronized、重入锁等比较重的锁，基本是通过 自旋+CAS 实现；
 * （8）对于入队之后，先自旋一定次数后再调用LockSupport.park()或LockSupport.parkNanos阻塞；
 *
 */
public class TestLinkedTransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                queue.offer("hello1");//插入队尾，队列是无界的，理论上永远不会阻塞线程
                queue.put("hello2"); //和offer方法一模一样，只是没有返回值
                queue.add("hello3"); //和offer方法一模一样
                System.out.println("1线程take：" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("2线程take：" + queue.take());
                System.out.println("2线程take：" + queue.poll()); //poll方法，没有元素的时候不会阻塞，返回null
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("主线程take：" + queue.take());//返回并移除队列头结点，如果队列为空，会阻塞
        System.out.println("主线程被唤醒1");
        queue.transfer("hello4"); //一定阻塞，直到元素被别人拿走，会被唤醒
        System.out.println("主线被程唤醒2");
    }
}
