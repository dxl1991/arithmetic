package AQS_lock;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author dengxinlong
 * @Date 2020/5/10 11:31
 * @slogan CODE IS TRUTH
 * 一个由链表结构组成的双向阻塞队列，即可以从队列的两端插入和移除元素
 */
public class TestLinkedBlockingDeque {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(2);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                deque.putFirst("hello");
                Thread.sleep(2000);
                System.out.println(deque.takeLast());
                System.out.println(deque.takeLast());
                //                System.out.println(deque.takeLast());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //        deque.take();
        System.out.println(deque.poll());//不会阻塞，返回null
        System.out.println(deque.takeLast());//没有元素会阻塞
        deque.putFirst("hello1");
        deque.putFirst("hello2");
        deque.putFirst("hello3");//满了，会阻塞
        System.out.println("主线程完成");

    }
}
