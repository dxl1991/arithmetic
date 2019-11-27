package sort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------冒泡排序-------------
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * 每一趟可以选出一个最大的数,放在后面
 * 列表相邻的两个数进行比较交换
 * 一趟排序完成后，无序区减少一个数，有序区增加一个数
 */
public class BubbleSort {

    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length - 1;i++){
            for(int j=0;j<array.length - i - 1;j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
