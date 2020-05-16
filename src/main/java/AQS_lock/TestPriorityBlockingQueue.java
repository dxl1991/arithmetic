package AQS_lock;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author dengxinlong
 * @Date 2020/5/8 21:19
 * @slogan CODE IS TRUTH
 *  队列里的元素必须继承Comparable接口，或者构造方法里指定Comparator
 *  数组存放数据，构造堆，排序，优先出队
 */
public class TestPriorityBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Node> queue =
                new PriorityBlockingQueue<>(2, Comparator.comparingInt((Node o) -> -o.num));
        queue.add(new Node(3));
        System.out.println(queue.poll());
        queue.add(new Node(2));
        queue.add(new Node(4));
        queue.add(new Node(1));
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.take()); //没有元素的时候会阻塞
    }

    private static class Node {
        private int num;

        Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }

        //        @Override
        //        public int compareTo(Node o) {
        //            return num - o.num;
        //        }
    }
}
