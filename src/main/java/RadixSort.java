import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author dengxinlong
 * @Date 2019/10/17
 * -------------基数排序-------------
 * 时间复杂度：O(n*k) k是最大元素的位数
 * 空间复杂度：O(n+k)
 * 稳定性：稳定
 *
 * 前提：需要知道列表里的最大数的位数
 * 思路：把元素按照个位数分桶，然后依次拿出；十位数分桶，然后依次拿出；...直到最高位
 *       因为桶是按照0-9有序的，把每个位都分了一遍并依次拿出，所有最终列表有序
 * 最大元素越位数越小，速度越快
 */
public class RadixSort {

    public static void radixSort(int[] array,int max){
        int it = 0;//位数
        while(10 * it <= max){
            Map<Integer,List<Integer>> buckets = new HashMap<Integer, List<Integer>>();
            for(int val : array){
                int digit; //元素第it位的数
                if(it == 0){
                    digit = val % 10;
                }else{
                    digit = (val / (10 * it)) % 10;
                }
                List<Integer> tempList = buckets.get(digit);
                if(tempList == null){
                    tempList = new ArrayList<Integer>();
                    buckets.put(digit,tempList);
                }
                tempList.add(val);
            }
            //把所有数据依次放入列表
            int index = 0;
            for( List<Integer> tempList : buckets.values()){
                for(int val : tempList){
                    array[index++] = val;
                }
            }
            it++;
        }
    }
}
