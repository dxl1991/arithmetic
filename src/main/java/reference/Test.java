package reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dxl 当一个对象仅仅被weak reference指向, 而没有任何其他strong reference指向的时候, 如果GC运行, 那么这个对象就会被回收
 * @slogan CODE IS TRUTH
 * @date 2020/3/11 10:29
 */
public class Test {
    public static void main(String[] args) {
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
        while (true){

        }
    }
}
