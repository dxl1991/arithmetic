package reference;

import java.lang.ref.WeakReference;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/3/17 10:46
 */
public class B {
    WeakReference<A> a; //当a只有这个这个弱引用 指向的时候就会被回收
    B(WeakReference<A> a){
      this.a = a;
    }
}
