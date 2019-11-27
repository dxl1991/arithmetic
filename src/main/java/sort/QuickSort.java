package sort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------快速排序-------------
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(logn)  因为递归，所以有空间复杂度
 * 稳定性：不稳定
 * 步骤：
 *    1、把第一个元素找到自己的位置，使左边比自己小，右边比自己大
 *    2、把左边重复以上步骤，右边重复以上步骤
 * 注意：当列表倒序的时候，时间复杂度是O(n²) 空间复杂度是O(n)
 */
public class QuickSort {
    public static void quik_sort(int[] array) {
        quik_sort(array, 0, array.length - 1);
    }

    private static void quik_sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = partition(array, left, right);
            quik_sort(array, left, mid - 1);
            quik_sort(array, mid + 1, right);
        }
    }

    //把left下标的元素找到自己的位置，使左边比自己小，右边比自己大
    public static int partition(int[] array, int left, int right) {
        int temp = array[left];
        //左小标和右下标像中间靠拢，两边元素进行交换调整，当左右下标相等就找到了自己的下标
        while (left < right) {
            while (left < right && temp <= array[right]) {
                right--;
            }
            array[left] = array[right];
            while (left < right && temp >= array[left]) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        return left;
    }
}
