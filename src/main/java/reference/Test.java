package reference;

import java.lang.ref.WeakReference;

/**
 * 当一个对象仅仅被weak reference指向, 而没有任何其他strong reference指向的时候, 如果GC运行, 那么这个对象就会被回收
 * netty里有个ConcurrentWeakKeyHashMap可以使用
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/3/11 10:29
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        thread.setName("test-weakReference");
        A a = new A();
        B b = new B(new WeakReference<>(a)); //a会被回收
        //        C c = new C(a);//a不会被回收
        a = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
        System.out.println("gc...");
        while (true) {

        }
    }
}
