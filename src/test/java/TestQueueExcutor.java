import org.junit.Test;
import queue.MessageEvent;
import queue.SelfDriveRunnableQueue;

import java.util.concurrent.Executors;

public class TestQueueExcutor {

    @Test
    public void test(){
        SelfDriveRunnableQueue excutor = new SelfDriveRunnableQueue(Executors.newFixedThreadPool(4));
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<10;i++){
                    final int finalI = i;
                    excutor.addMessage(new MessageEvent(excutor) {
                        @Override
                        public void handler() {
                            System.out.println("a:"+ finalI);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<10;i++){
                    final int finalI = i;
                    excutor.addMessage(new MessageEvent(excutor) {
                        @Override
                        public void handler() {
                            System.out.println("b:"+ finalI);
                        }
                    });
                }
            }
        }).start();
    }
}
