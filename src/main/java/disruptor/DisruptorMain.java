package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengxinlong
 * @date 2021/1/27 11:56
 * @version 1.0
 * 自带消费者线程的阻塞队列
 */
public class DisruptorMain {
    public static void main(String[] args) throws Exception
    {
        // 队列中的元素
        class Element {

            private int value;

            public int get(){
                return value;
            }

            public void set(int value){
                this.value= value;
            }

        }
        AtomicInteger i = new AtomicInteger();
        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("newThread,"+Thread.currentThread());
                return new Thread(r, "simpleThread" + i.incrementAndGet());
            }
        };

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                Element element = new Element();
                System.out.println("new Element():"+element);
                return element;
            }
        };

        // 处理Event的handler
        EventHandler<Element> handler1 = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch) throws InterruptedException {
                System.out.println("Element1: " + element.get() + ",threadName="+Thread.currentThread().getName() + ","+element);
                Thread.sleep(1000);
            }
        };

        EventHandler<Element> handler2 = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch) throws InterruptedException {
                System.out.println("Element2: " + element.get() + ",threadName="+Thread.currentThread().getName() + ","+element);
                Thread.sleep(1000);
            }
        };

        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小（在队列里初始化16个element）
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.MULTI, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler1,handler2,handler1); //同一个event需要被多个Handler消费,有几个Handler就创建几个线程

        // 启动disruptor的线程
        RingBuffer<Element> ringBuffer = disruptor.start();

       // RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++)
        {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next(); //队列没有多余的Element可以用就会阻塞
            try
            {
                // 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.set(l);
            }
            finally
            {
                ringBuffer.publish(sequence); //发布事件，给Handler用
            }
            Thread.sleep(10);
            System.out.println("l="+l);
        }
    }
}
