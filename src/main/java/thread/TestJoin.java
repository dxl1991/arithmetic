package thread;

/**
 * Join合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
 * t.join()方法只会使主线程进入等待池并等待t线程执行完毕后才会被唤醒。并不影响同一时刻处在运行状态的其他线程。
 * t.join()需要等t.start()执行之后执行才有效果
 * 线程在die的时候会自动调用自身的notifyAll方法
 */
public class TestJoin implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread joinThread = new Thread(new TestJoin());
        joinThread.start();
        for (int i = 0; i < 100; i++) {
            if (i==80){
                //强制执行，让这个线程执行完，我才能往下执行
                joinThread.join(); //底层也是用wait()方法
            }
            System.out.println("我是主线程:"+i);
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("我是要插队的线程:"+i);
        }
    }
}
