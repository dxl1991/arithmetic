package reference;

import java.lang.ref.WeakReference;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/3/17 10:46
 */
public class C {
    A a; //这是个强引用
    C(A a){
      this.a = a;
    }
}
