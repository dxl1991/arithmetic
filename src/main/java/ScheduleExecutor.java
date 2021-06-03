
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleExecutor {

    private ScheduledThreadPoolExecutor executor;
    private String name;

    public ScheduleExecutor(String name, int threadCount) {
        this.name = name;
        executor = new ScheduledThreadPoolExecutor(threadCount, new ThreadFactory() {
            private final AtomicInteger idCounter = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, name + idCounter.incrementAndGet());
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }
        });

        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
    }

    public String getName() {
        return name;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return executor.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return executor.scheduleWithFixedDelay(command, initialDelay, period, unit);
    }

    public void schedule(Runnable command, long delay, TimeUnit unit) {
        executor.schedule(command, delay, unit);
    }

    public void stop() {
        try {

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

        } catch (Exception e) {
        }
    }
}
