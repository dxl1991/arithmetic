package AQS_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/15 10:27
 * 可重复使用
 * 场景：所有线程到达一个状态后，然后各自去做自己的事情。await一次，计数器减1，线程阻塞，计数器减到0后所有线程解除阻塞
 */
public class TestCyclicBarrier {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        new TestCyclicBarrier().begin();
    }

    private void begin() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Student(i, i)).start();
        }
        System.out.println("完成就餐安排，等人到齐");
    }

    private class Student implements Runnable {
        private int sec;
        private int name;

        public Student(int sec, int name) {
            this.sec = sec;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sec * 1000);
                System.out.println(name + "号到达");
                cyclicBarrier.await();
                //TODO 计数器减至0后执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
