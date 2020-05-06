package design_mode.factory;

/**
 * 普通工厂模式
 */
public class SendFactory1 {
    public Sender produceSender(String type){
        switch (type){
            case "mail":
                return new MailSender();
            case "sms":
                return new SmsSender();
            default:
                System.out.println("类型错误");
                return null;
        }
    }
}
