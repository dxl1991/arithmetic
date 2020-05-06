package design_mode.factory;

/**
 * 抽象工厂模式（Abstract Factory）
 * 工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则
 * 抽象工厂模式解决这个问题，通过创建多个工厂类
 */
public interface Provider {
    public Sender produce();
}
