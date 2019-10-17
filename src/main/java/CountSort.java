/**
 * @Author dengxinlong
 * @Date 2019/10/16
 * -------------计数排序-------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(m)
 * 稳定性：不稳定
 *
 * 条件：已知列表中的元素都是0到m之间
 * 实现：把列表遍历一遍，统计相同元素的个数，最后把每个元素按个数拼接成一个有序列表
 */
public class CountSort {

    public static void countSort(int[] array,int max){
        int[] tempArray = new int[max+1];
        for(int i : array){
            tempArray[i] += 1;
        }
        int index = 0;
        for(int i=0;i<tempArray.length;i++){
            for(int j=0;j<tempArray[i];j++){
                array[index++] = i;
            }
        }
    }
}
