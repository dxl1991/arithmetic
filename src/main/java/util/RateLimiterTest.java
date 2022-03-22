package util;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/** https://www.cnblogs.com/ynyhl/p/12935403.html
 * 常见的限流算法
 * 1、通过控制最大并发数来进行限流 ：一大波人去商场购物，必须经过一个门口，门口有个门卫，兜里面有指定数量的门禁卡，来的人先去门卫那边拿取门禁卡，拿到卡的人才可以刷卡进入商场，拿不到的可以继续等待。进去的人出来之后会把卡归还给门卫，门卫可以把归还来的卡继续发放给其他排队的顾客使用
 * 2、使用漏桶算法来进行限流 ：漏桶算法思路很简单，水（请求）先进入到漏桶里，漏桶以一定的速度出水，当水流入速度过大会直接溢出，可以看出漏桶算法能强行限制数据的传输速率
 * 3、使用令牌桶算法来进行限流 ：令牌桶算法的原理是系统以恒定的速率产生令牌，然后把令牌放到令牌桶中，令牌桶有一个容量，当令牌桶满了的时候，再向其中放令牌，那么多余的令牌会被丢弃；当想要处理一个请求的时候，需要从令牌桶中取出一个令牌，如果此时令牌桶中没有令牌，那么则拒绝该请求。从原理上看，令牌桶算法和漏桶算法是相反的，一个“进水”，一个是“漏水”。这种算法可以应对突发程度的请求，因此比漏桶算法好
 * @author dengxinlong
 * @date 2021/10/27 17:30
 */
public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {
        //采用令牌桶算法
        RateLimiter rateLimiter = RateLimiter.create(1000,1,TimeUnit.SECONDS);//每秒1000次请求，但是需要均匀的请求，即一毫秒一个。如果一瞬间来10次，后面的9次也是请求不通过。
        //RateLimiter rateLimiter = RateLimiter.create(1000); //和上面一样，但是可以休息1秒，提前存储1秒（1000）个令牌
        Thread.sleep(1000); //休息一下，最多可以存储1秒（1000）个令牌
        for(int i=0;i<1020;i++){
            //Thread.sleep(1); //SmoothWarmingUp模式下不sleep，后面的9次都是false
            System.out.println(rateLimiter.tryAcquire());
        }
    }

    //Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量
    static Semaphore semaphore = new Semaphore(5);

    public static void test() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                    flag = semaphore.tryAcquire(100, TimeUnit.MICROSECONDS);
                    if (flag) {
                        //休眠2秒，模拟下单操作
                        System.out.println(Thread.currentThread() + "，尝试下单中。。。。。");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        System.out.println(Thread.currentThread() + "，秒杀失败，请稍微重试！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag) {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
