package design_mode.adapter;

public class ExpensiveMP4 implements MP4{
    @Override
    public void play() {
        System.out.println("这是mp4的实现类");
    }
}
