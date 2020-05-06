package design_mode.factory;

public class Test {
    public static void main(String[] args) {
        SendFactory1 sendFactory1 = new SendFactory1();
        Sender sender1 = sendFactory1.produceSender("mail");
        sender1.send();

        SendFactory2 factory2 = new SendFactory2();
        Sender sender2 = factory2.produceSms();
        sender2.send();

        Sender sender3 = SendFactory3.produceMail();
        sender3.send();

        Provider provider = new SendMailFactory();
        Sender sender4 = provider.produce();
        sender4.send();
    }
}
