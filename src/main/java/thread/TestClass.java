package thread;

public class TestClass implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        TestClass testClass = new TestClass();
        new Thread(testClass).start();
        Thread.sleep(1000);
        testClass.stop();
    }
    boolean flag=true;
    @Override
    public void run() {
        while (flag){
            System.out.println("run ......Thread");
        }

    }
    private void stop(){
        this.flag=false;

    }
}
