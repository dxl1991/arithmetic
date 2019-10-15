/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------插入排序-------------
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * 思想：从无序区拿牌插入到有序区。
 *       插入有序区的过程就是从有序区的右边开始把数据逐个比较，不满足的就往右挪一个位置，直到找到自己的位置
 *       把第一个元素当做一个有序区，然后从第二个元素开始往有序区插入
 *
 */
public class InsertSort {

    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i-1;
            int temp = array[i];
            while (j >= 0 && array[j] > temp){
                array[j+1] = array[j]; //元素往右挪
                j--;
            }
            array[j+1] = temp;
        }
    }
}
