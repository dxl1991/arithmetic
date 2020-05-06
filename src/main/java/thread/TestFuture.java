package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask1 = new FutureTask<>(()->{
            Thread.sleep(1000);
            return "第一步完成";
        });
        new Thread(futureTask1).start();
        FutureTask<String> futureTask2 = new FutureTask<>(()->{
            Thread.sleep(1500);
            return "第二步完成";
        });
        new Thread(futureTask2).start();
        System.out.println(futureTask1.get()); //阻塞直到线程完成
        System.out.println(futureTask2.get());//阻塞直到线程完成
        System.out.println("全部完成");
    }
}
