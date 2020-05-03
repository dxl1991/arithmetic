/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/22 18:23
 */
public class TestA implements Cloneable{
    String s;
    TestA(String s){
        this.s = s;
        System.out.println(s);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return s;
    }
}
