package AQS_lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/15 10:12
 * 不可重复使用
 * 场景：多人赛跑，所有人到达终点后开始排名（所有线程到达一个状态后，然后去做别的事情）
 */
public class TestCountDownLatch {
    private CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) {
        new TestCountDownLatch().begin();
    }

    private void begin(){
        System.out.println("开始赛跑");
        Random random = new Random(System.currentTimeMillis());
        for (int i=0;i<4;i++){
            int result = random.nextInt(3) + 1;
            new Thread(new Runner(result,i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO 所有线程都countDown()后执行
        System.out.println("所有人都跑完了，裁判开始算成绩");
    }

    private class Runner implements Runnable{
        private int result;
        private int name;
        public Runner(int result,int name){
            this.result = result;
            this.name = name;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(result * 1000);
                System.out.println(name+"号完成比赛");
                countDownLatch.countDown();
                //TODO 所有线程都countDown()后执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
