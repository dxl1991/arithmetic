package reference;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/3/17 10:46
 */
public class A {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("A finalized....");
    }
}
