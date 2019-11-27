package sort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------堆排序-------------
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * 步骤：
 *    1、构造堆
 *    2、堆的最后一个节点和根节点交换，然后进行一次堆的向下调整
 *    3、堆的倒数第二个节点和根节点交换，然后进行一次堆的向下调整
 *    4、堆的倒数第n个节点和根节点交换，然后进行一次堆的向下调整
 *
 * 构造堆：从最后面的子节点开始调整，把子节点当做一个堆，进行一次堆的向下调整
 *        使其变成一个正确的堆，然后再向上一层进行堆的向下调整，直至最上层
 *
 * 堆的一次向下调整：除了根节点底下的子节点都满足堆的特性，然后把根节点向下找到自己的位置
 * 完全二叉树：满二叉树的最后几个节点可以没有值，前面的节点都是连续的
 * 堆：一种特殊的完全二叉树，根节点大于（小于）子节点
 */
public class HeapSort {
    //向下调整
    public static void sift(int[] array, int low, int high) {
        int temp = array[low]; //根节点（需要调整的节点）
        int i = low;
        int j = 2 * i + 1; //左孩子
        while (j <= high) {
            if (j + 1 <= high && array[j] < array[j + 1]) {
                j++; //变右孩子
            }
            if (temp < array[j]) {
                array[i] = array[j]; //孩子往上移
                i = j;
                j = 2 * i + 1;
            } else {
                break; //根节点找到了自己的位置
            }
        }
        array[i] = temp;
    }

    public static void heapSort(int[] array) {
        //建堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            sift(array, i, array.length - 1);
        }
        //排序
        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            sift(array, 0, i - 1);
        }
    }
}
