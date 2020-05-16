/**
 * @Author dengxinlong
 * @Date 2020/5/10 15:41
 * @slogan CODE IS TRUTH
 * 这个例子中，在server模式下，线程永远也停不下来
 */
public class VisibilityTest extends Thread {
    private boolean stop;

    public void run() {
        int i = 0;
        while (!stop) {
            i++;
        }
        System.out.println("finish loop,i=" + i);
    }

    public void stopIt() {
        stop = true;
    }

    public boolean getStop() {
        return stop;
    }

    public static void main(String[] args) throws Exception {
        VisibilityTest v = new VisibilityTest();
        v.start();
        Thread.sleep(1000); //如果没有这个sleep，也会使线程停下来
        v.stopIt();
        Thread.sleep(2000);
        System.out.println("finish main");
        System.out.println(v.getStop());
    }
}
