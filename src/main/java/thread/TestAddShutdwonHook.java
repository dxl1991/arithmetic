package thread;

/**
 * @Author dengxinlong
 * @Date 2020/5/16 23:01
 * @slogan CODE IS TRUTH
 * jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后，jvm才会关闭
 * kill -9 <pid> 很暴力，不会调用钩子函数ShutdownHook
 * kill <pid> 也就是kill -15 <pid> 很柔和，将会调用钩子函数ShutdownHook
 */
public class TestAddShutdwonHook {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("thread1..."));

        Thread thread2 = new Thread(() -> System.out.println("thread2..."));

        // 定义关闭线程
        Thread shutdownThread = new Thread(() -> System.out.println("shutdownThread..."));

        // jvm关闭的时候先执行该线程钩子
        Runtime.getRuntime().addShutdownHook(shutdownThread);

        thread1.start();
        thread2.start();
    }
}
