import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import reference.Test;
import singleton.ContainerSingleton;
import sort.RadixSort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    private static TestA a = new TestA("aaaaaaaaa");
    private TestA b = new TestA("bbbbbbbbb");
    TestClass(){
        System.out.println("cccccccc");
    }
    public static void main(String[] args){
        new TestClass();
//        int count = 1000;
//        int max = 100000;
//        int[] temp = new int[count];
//        Random random = new Random();
//        for(int i=0;i<count;i++){
//            temp[i] = random.nextInt(max);
//        }
//        //        quik_sort(temp);
//        //        heapSort(temp);
//        //        int[] temp2 = topK3(temp, 4);
//        RadixSort.radixSort(temp,max);
//        for (int i : temp) {
//            System.out.print(i + ",");
//        }
//        Executors.newCachedThreadPool();

    }








}
