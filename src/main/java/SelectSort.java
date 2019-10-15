/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------选择排序-------------
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * 步骤：
 *    1、一趟排序记录最小的数，放到第一个位置
 *    2、再一趟排序记录倒数第二小的数，放到第二个位置
 *    3、重复2步骤
 */
public class SelectSort {

    public static void selectSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
