package queue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        long ctime = System.currentTimeMillis();
        SelfDriveRunnableQueue excutor = new SelfDriveRunnableQueue(Executors.newFixedThreadPool(4));
//        List<Integer> aa = Collections.synchronizedList(new ArrayList<>());
//        List<Integer> aa = new CopyOnWriteArrayList<>();
        Vector<Integer> aa = new Vector<>();
        CountDownLatch countDownLatch = new CountDownLatch(20000);
        new Thread(()-> {
                for(int i = 0;i<10000;i++){
                    final int finalI = i;
                    excutor.addMessage(new MessageEvent(excutor) {
                    @Override
                    public void handler() {
                        aa.add(finalI);
                        countDownLatch.countDown();
                    }
                });
            }
        }).start();
//        List<Integer> bb = Collections.synchronizedList(new ArrayList<>());
//        List<Integer> bb = new CopyOnWriteArrayList<>();
        Vector<Integer> bb = new Vector<>();
        new Thread(()-> {
            for(int i = 0;i<10000;i++){
                final int finalI = i;
                excutor.addMessage(new MessageEvent(excutor) {
                    @Override
                    public void handler() {
                        bb.add(finalI);
                        countDownLatch.countDown();
                    }
                });
            }
        }).start();
        countDownLatch.await();
        System.out.println("aa length="+aa.size());
        System.out.println("bb length="+bb.size());
        int tag = -1;

        for(int a : aa){
            if(a > tag){
                tag = a;
            }else{
                System.out.println("a error");
            }
        }
        tag = -1;
        for(int b : bb){
            if(b > tag){
                tag = b;
            }else{
                System.out.println("b error");
            }
        }
        System.out.println("cost time = "+(System.currentTimeMillis() - ctime));
    }
}
