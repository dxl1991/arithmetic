package design_mode.decorator;

/**
 * 装饰器模式
 * 定义：在不改变原有对象的基础之上，将功能附加到对象上。提供了比继承更有弹性的替代方案（扩展原有对象功能）
 * 适用场景
 *   1、扩展一个类的功能或者给一个类添加附加职责
 *   2、给一个对象动态的添加功能，或动态撤销功能。
 * 和代理模式区别：
 *  代理，偏重因自己无法完成或自己无需关心，需要他人干涉事件流程，更多的是对对象的控制。
 *  装饰，偏重对原对象功能的扩展，扩展后的对象仍是是对象本身。
 */
public class Test {
    public static void main(String[] args) {
        ABattercake aBattercake;
        aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);

        System.out.println(aBattercake.getDesc()+" 销售价格:"+aBattercake.cost());
    }
}
