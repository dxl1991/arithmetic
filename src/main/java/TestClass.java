import java.util.Random;

import sort.RadixSort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    public static void main(String[] args){
        int count = 1000;
        int max = 100000;
        int[] temp = new int[count];
        Random random = new Random();
        for(int i=0;i<count;i++){
            temp[i] = random.nextInt(max);
        }
        //        quik_sort(temp);
        //        heapSort(temp);
        //        int[] temp2 = topK3(temp, 4);
        RadixSort.radixSort(temp,max);
        for (int i : temp) {
            System.out.print(i + ",");
        }
    }








}
