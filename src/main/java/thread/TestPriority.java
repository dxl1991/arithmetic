package thread;

/**
 * 线程的优先级用数字表示，范围从1~10
 * Thread.MIN_PRIORITY = 1;
 * Thread.MAX_PRIORITY = 10;
 * Thread.NORM_PRIORITY = 5;
 * 优先级低只是意味着获得调度的概率低.并不是优先级低就不会被调用了.这都是看CPU的调度
 */
public class TestPriority {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());


        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority);
        Thread thread2 = new Thread(myPriority);
        Thread thread3 = new Thread(myPriority);
        Thread thread4 = new Thread(myPriority);
        Thread thread5 = new Thread(myPriority);


        thread1.setPriority(1);
        thread1.start();

        thread2.setPriority(4);
        thread2.start();

        thread3.setPriority(8);
        thread3.start();

        thread4.setPriority(9);
        thread4.start();

        thread5.setPriority(10);
        thread5.start();

    }



}


class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }
}
