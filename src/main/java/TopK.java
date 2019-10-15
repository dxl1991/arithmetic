import java.util.Arrays;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 * 取数组中前k个最小的数的三种实现方式
 */
public class TopK {
    //取数组中前k个最小的数(用堆排序实现)
    public static int[] topK(int[] array, int k) {
        int[] heap = Arrays.copyOf(array, k);
        //建堆
        for (int i = (k - 2) / 2; i >= 0; i--) {
            HeapSort.sift(heap, i, k - 1);
        }
        //遍历（把前k个最小的数拿出来建成一个堆）
        for (int i = k; i < array.length; i++) {
            if (array[i] < heap[0]) {
                heap[0] = array[i];
                HeapSort.sift(heap, 0, k - 1);
            }
        }
        //排序
        for (int i = k - 1; i >= 0; i--) {
            int temp = heap[i];
            heap[i] = heap[0];
            heap[0] = temp;
            HeapSort.sift(heap, 0, i - 1);
        }
        return heap;
    }

    //取数组中前k个最小的数(用冒泡排序实现)
    public static int[] topK2(int[] array, int k) {
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            heap[i] = array[array.length - 1 - i];
        }
        return heap;
    }

    //取数组中前k个最小的数(用选择排序实现)
    public static int[] topK3(int[] array, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return Arrays.copyOf(array, k);
    }
}
