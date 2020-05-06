package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestFuture2 extends RecursiveTask<Integer> {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        TestFuture2 calculator = new TestFuture2(10);
        forkJoinPool.execute(calculator);
    }

    private Integer n;

    public TestFuture2(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        TestFuture2 calculator = new TestFuture2(n - 1);

        calculator.fork();

        return n * n + calculator.join();
    }
}