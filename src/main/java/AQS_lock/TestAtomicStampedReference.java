package AQS_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author dengxinlong
 * @date 2021/8/31 9:56
 * ABA问题：一个线程把数据A变为了B，然后又重新变成了A。此时另外一个线程读取的时候，发现A没有变化，就误以为是原来的那个A
 * AtomicStampedReference是一个带有时间戳的对象引用，能很好的解决CAS机制中的ABA问题
 */
public class TestAtomicStampedReference {
    private static AtomicInteger index = new AtomicInteger(10);
    private static AtomicStampedReference<Integer> stampedRef = new AtomicStampedReference<>(10,1);

    public static void main(String[] args) {
        int stamp = stampedRef.getStamp();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
            stampedRef.compareAndSet(10,11,stamp,stamp + 1);
            System.out.println(Thread.currentThread().getName() + "第二次版本号：" + stampedRef.getStamp());
            stampedRef.compareAndSet(11,10,stampedRef.getStamp(),stampedRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号：" + stampedRef.getStamp());
        },"张三").start();
        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean success = stampedRef.compareAndSet(10,12,stamp,stamp + 1);
                System.out.println(Thread.currentThread().getName() + "第二次版本号：" + stampedRef.getStamp() + ",是否成功：" + success);
                System.out.println(Thread.currentThread().getName() + "当前实际值：" + stampedRef.getReference());
            }catch (Exception e){
                e.printStackTrace();
            }
        },"李四").start();
    }
}
