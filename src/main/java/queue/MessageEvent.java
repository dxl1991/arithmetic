package queue;

public abstract class MessageEvent implements Runnable{
    private SelfDriveRunnableQueue queueExcutor;
    public MessageEvent(SelfDriveRunnableQueue queueExcutor){
        this.queueExcutor = queueExcutor;
    }
    public abstract void handler();
    @Override
    public void run() {
        try{
            handler();
        }finally {
            queueExcutor.executeNext();
        }
    }
}
