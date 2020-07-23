package jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author dengxinlong
 * @date 2020/7/20 14:14
 * @version 1.0
 * jna 调用 dll动态库例子
 * java -> c++ , c++ -> c#
 * 将JNADll.dll和JNATest.dll放jdk的bin目录
 */
public class TestMyJna {

    public interface SumJna extends Library{
        SumJna INSTANCE = Native.loadLibrary("JNADll",SumJna.class);
        /* 需要调用的方法,方法名与c++方法名相同 */
        String getName(String value);

        int getAge(int age);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));// 输出当前jdk版本号
        System.out.println(System.getProperty("sun.arch.data.model"));// 输出当前jdk所用平台

        int age = SumJna.INSTANCE.getAge(3);
        String name = SumJna.INSTANCE.getName("小红");
        System.out.println(name + "今年" + age + "岁");
    }
}
