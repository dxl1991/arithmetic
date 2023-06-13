package AQS_lock;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author dengxinlong
 * @Date 2020/5/8 22:11
 * @slogan CODE IS TRUTH
 * 队列里用了PriorityQueue存数据，所以是有序出队，每个元素还有一个延时时间，到了延时时间才能出队（不会按延迟时间排序，可以自己实现排序规则）
 */
public class TestDelayQueue {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Node> queue = new DelayQueue<>();
        queue.add(new Node(5, "5秒"));
        queue.add(new Node(10, "10秒"));
        while (true) {
            Node node = queue.take();//没有元素，或者元素还没到延时时间，会阻塞到延时时间
            System.out.println(node);
            node.updateTime();
            queue.add(node);
        }
    }

    private static class Node implements Delayed {
        private int sec;
        private String info;
        private long time;

        Node(int sec, String info) {
            this.sec = sec;
            this.info = info;
            updateTime();
        }

        private void updateTime(){
            time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(sec);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Node node = (Node) o;
            return time > node.time ? 1 : -1;
        }

        @Override
        public String toString() {
            return info;
        }
    }
}
