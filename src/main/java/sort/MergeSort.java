package sort;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * -------------归并排序-------------
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 * 一次归并：将两个有序列表合成一个有序列表
 * 步骤：
 *     1、分解 - 将列表越分越小，直到分成一个元素
 *     2、合并 - 将两个有序列表归并，列表越来越大
 */
public class MergeSort {

    //一次归并（low到mid有序，mid到high有序）
    public static void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int[] temp = new int[high - low + 1];
        int index = 0;
        while (i <= mid && j <= high) {
            if (array[i] < array[j]) {
                temp[index++] = array[i];
                i++;
            } else {
                temp[index++] = array[j];
                j++;
            }
        }
        while (i <= mid) {
            temp[index++] = array[i];
            i++;
        }
        while (j <= high) {
            temp[index++] = array[j];
            j++;
        }
        index = low;
        for (int value : temp) {
            array[index++] = value;
        }
    }

    public static void merge_sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge_sort(array, low, mid);
            merge_sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    //归并排序
    public static void merge_sort(int[] array) {
        merge_sort(array, 0, array.length - 1);
    }
}
