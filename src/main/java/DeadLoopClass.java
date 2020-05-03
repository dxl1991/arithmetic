public class DeadLoopClass{
    static class DeadLoopClass1{
        static{
            if(true){
                System.out.println("thread "+Thread.currentThread().getName()+" init DeadLoopClass");
                while (true){}
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start");
                new DeadLoopClass1();
                System.out.println(Thread.currentThread().getName()+" over");
            }
        };
        Thread thread1 = new Thread(runnable,"1");
        Thread thread2 = new Thread(runnable,"2");
        thread1.start();
        thread2.start();
    }
}
