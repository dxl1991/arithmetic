package queue;

import java.util.concurrent.*;

/**
 * 自驱动队列
 * @param <T>
 */
public class SelfDriveRunnableQueue<T extends Runnable> {
    private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning;
    private ExecutorService executorService;

    public SelfDriveRunnableQueue(ExecutorService executorService){
        this.executorService = executorService;
    }

    public void addMessage(T event){
        if(event == null){
            return;
        }
        queue.offer(event);
        if(!isRunning){
            isRunning = true;
            executorService.execute(queue.poll());
        }
    }

    protected void executeNext(){
        T event = queue.poll();
        if(event != null){
            isRunning = true;
            executorService.execute(event);
        }else{
            isRunning = false;
        }
    }

}
