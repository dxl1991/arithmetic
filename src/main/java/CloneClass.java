/**
 * 只有实现了Cloneable接口才能调用Object的clone()方法，否则报错
 * Object的clone()方法会创建一个新对象，对象的成员变量和老的对象一样，包括基础类型和引用类型，是一种浅拷贝
 * 深拷贝：重写Object的clone()方法，把引用类型变量也clone一份
 */
public class CloneClass implements Cloneable{
    public String s;
    public int i;
    public TestA testA;

    CloneClass(String s,int i){
        this.s = s;
        this.i = i;
        this.testA = new TestA("t");
    }

    //深拷贝
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneClass cloneClass = (CloneClass) super.clone();
        cloneClass.testA = (TestA) this.testA.clone();
        return cloneClass;
    }

    @Override
    public String toString() {
        return s + ":" + i+";"+testA.s;
    }

    public static void main(String[] args) {
        CloneClass cloneClass = new CloneClass("dxl",3);
        try {
            CloneClass b = (CloneClass) cloneClass.clone();
            System.out.println(b.s == cloneClass.s);
            System.out.println(b.testA == cloneClass.testA);
            System.out.println(b == cloneClass);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
