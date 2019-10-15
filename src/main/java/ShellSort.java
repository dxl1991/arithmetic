/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------希尔排序-------------
 * 时间复杂度：比较复杂，和选取的gap序列有关
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * 希尔排序是一种分组插入排序算法
 * 步骤：
 *   1、首先取一个整数d1 = n/2 ，将元素分为d1个组，每组相邻元素之间距离为d1，在各组内进行直接插入排序
 *   2、取第二个整数d2 = d1/2 ，重复上述分组排序过程，知道di = 1，即所有元素在同一组内进行直接插入排序
 *  希尔排序每趟并不能使某些元素有序，但是使整体数据越来越接近有序；最后一趟排序使得所有元素有序
 */
public class ShellSort {

    public static void insertSortGap(int[] array,int gap){
        for(int i=gap;i<array.length;i++){
            int j = i-gap;
            int temp = array[i];
            while (j >= 0 && array[j] > temp){
                array[j+gap] = array[j]; //元素往右挪
                j -= gap;
            }
            array[j+gap] = temp;
        }
    }

    public static void sellSort(int[] array){
        int gap = array.length / 2;
        while (gap >= 1){
            insertSortGap(array,gap);
            gap /= 2;
        }
    }
}
