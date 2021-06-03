package guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;

/**
 * @author dengxinlong
 * @date 2021/5/12 11:20
 * @version 1.0
 * 一个不可变、线程安全的列表集合，它只会获取传入对象的一个副本，而不会影响到原来的变量或者对象
 * ImmutableList创建不可变对象有两种方法，一种是使用静态of方法，另外一种是使用静态内部类Builder
 */
public class ImmutableTest {
    public static void main(String[] args) {
        ImmutableList<Integer> list1 = ImmutableList.<Integer>builder().add(1).add(2).add(3).build();
        ImmutableList<Integer> list2 = ImmutableList.of(1,2,3);
        List<Integer> list3 = ImmutableList.copyOf(Arrays.asList(1,2,3));
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        for (int a : list1){
            System.out.println(a);
        }
        //list1.add(4); //不支持修改元素
        ImmutableMap<Integer, String> map = ImmutableMap.<Integer, String>builder().put(1,"1").put(2,"2").build();
        System.out.println(map);
        map.computeIfAbsent(3,k->k.toString()); //也是不支持修改的
        //map.put(3,"3"); //不支持修改元素
    }
}
