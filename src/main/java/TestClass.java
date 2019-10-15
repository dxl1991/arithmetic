import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.Arrays;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    public static void main(String[] args){
        int[] temp = {3, 5, 6, 1, 7, 9, 8, 4, 2};
        //        quik_sort(temp);
        //        heapSort(temp);
        //        int[] temp2 = topK3(temp, 4);
        ShellSort.sellSort(temp);
        for (int i : temp) {
            System.out.print(i + ",");
        }
    }








}
