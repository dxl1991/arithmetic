import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;

/**
 * @Author dengxinlong
 * @Date 2020/5/9 21:00
 * @slogan CODE IS TRUTH
 *  对象头占固定12字节;对象地址占4字节;如果对象总大小不能被8整除，就要补齐字节数
 */
public class TestJol {
    public static void main(String[] args) {
        Node node = new Node();
        System.out.println(ClassLayout.parseInstance(node).toPrintable());//打印对象内存结构
    }

    private static class Node {
        int a = 2;
        int[] b;
        HashMap map = new HashMap(); //引用地址，4个字节
    }
}
