import java.util.ArrayList;
import java.util.List;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/22 18:23
 */
public class TestA implements Cloneable{
    {
        System.out.println("在构造函数之前运行");
        this.s = "bbbbbb";
    }
    String s;
    TestA(String s){
        System.out.println(this.s);
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

    public static void main(String[] args) {
//        new TestA("aaaaaaa");
        List<String> list = new ArrayList<>();
        list.add("aaaaaaaaaa");
        for(int i=0;i<list.size();){
            list.add(list.remove(i));
        }
    }
}
