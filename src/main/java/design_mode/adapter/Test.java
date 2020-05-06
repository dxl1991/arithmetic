package design_mode.adapter;

/**
 * 适配器模式
 * 作为连接两个接口的桥梁
 * java适配器模式有两种，类适配器和对象适配器
 */
public class Test {
    public static void main(String[] args) {
        MP4 mp4 = new ExpensiveMP4();
        PlayerAdapter adapter = new PlayerAdapter(mp4);
        adapter.action();
    }
}
