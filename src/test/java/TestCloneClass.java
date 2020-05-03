import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCloneClass {
    @Before
    public void before(){
        System.out.println("before");
    }
    @Test
    public void test(){
        CloneClass cloneClass = new CloneClass("dxl",3);
        try {
            CloneClass b = (CloneClass) cloneClass.clone();
            System.out.println(b.s == cloneClass.s);
            System.out.println(b.testA == cloneClass.testA);
//            System.out.println(b == cloneClass);
            Assert.assertEquals(b,cloneClass);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
