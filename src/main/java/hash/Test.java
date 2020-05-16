package hash;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author dengxinlong
 * @Date 2020/5/14 7:52
 * @slogan CODE IS TRUTH
 * -Xmx3072m -Xms3072m -Xmn900m -XX:SurvivorRatio=65536 -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -Xloggc:D:/gc.log
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Set<String> datas = new HashSet<>(10000000);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            datas.add(randomStr());
        }
        System.out.println("datra size=" + datas.size());
        System.out.println("数据随机完毕：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for (String data : datas) {
            ConsistentHashingWithVirtualNode.putData(data);
        }
        System.out.println("数据填充完毕：" + (System.currentTimeMillis() - time));
        ConsistentHashingWithVirtualNode.showServerInfo();
        time = System.currentTimeMillis();
        ConsistentHashingWithVirtualNode.removeServer("192.168.0.2:111");
        System.out.println("删除节点完毕：" + (System.currentTimeMillis() - time));
        CountDownLatch countDownLatch = new CountDownLatch(datas.size());
        time = System.currentTimeMillis();
        for (String data : datas) {
            executorService.execute(() -> {
                ConsistentHashingWithVirtualNode.getData(data);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("测试数据完毕：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        ConsistentHashingWithVirtualNode.addServer("192.168.0.2:111");
        System.out.println("增加节点完毕：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        ConsistentHashingWithVirtualNode.addServer("192.168.0.5:111");
        System.out.println("增加节点完毕：" + (System.currentTimeMillis() - time));
        ConsistentHashingWithVirtualNode.showServerInfo();
        CountDownLatch countDownLatch2 = new CountDownLatch(datas.size());
        time = System.currentTimeMillis();
        for (String data : datas) {
            executorService.execute(() -> {
                ConsistentHashingWithVirtualNode.getData(data);
                countDownLatch2.countDown();
            });
        }
        countDownLatch2.await();
        System.out.println("测试数据完毕：" + (System.currentTimeMillis() - time));
        executorService.shutdown();
    }

    static Random random = new Random();

    private static String randomStr() {
        int length = random.nextInt(10) + 10;
        char[] temp = new char[length];
        for (int i = 0; i < length; i++) {
            temp[i] = (char) (random.nextInt(122 - 65) + 65);
        }
        return new String(temp);
    }
}
