import java.util.Arrays;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    public static void main(String[] args){
        int[] temp = {3, 5, 6, 1, 7, 9, 8, 4, 2};
        //        quik_sort(temp);
        //        heapSort(temp);
        //        int[] temp2 = topK3(temp, 4);
        merge_sort(temp);
        for (int i : temp) {
            System.out.print(i + ",");
        }
    }

    private static void quik_sort(int[] array) {
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
    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
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

    //向下调整
    private static void sift(int[] array, int low, int high) {
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

    private static void heapSort(int[] array) {
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

    //取数组中前k个最小的数
    private static int[] topK(int[] array, int k) {
        int[] heap = Arrays.copyOf(array, k);
        //建堆
        for (int i = (k - 2) / 2; i >= 0; i--) {
            sift(heap, i, k - 1);
        }
        //遍历（把前k个最小的数拿出来建成一个堆）
        for (int i = k; i < array.length; i++) {
            if (array[i] < heap[0]) {
                heap[0] = array[i];
                sift(heap, 0, k - 1);
            }
        }
        //排序
        for (int i = k - 1; i >= 0; i--) {
            int temp = heap[i];
            heap[i] = heap[0];
            heap[0] = temp;
            sift(heap, 0, i - 1);
        }
        return heap;
    }

    //取数组中前k个最小的数(用冒泡排序实现)
    private static int[] topK2(int[] array, int k) {
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
    private static int[] topK3(int[] array, int k) {
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

    //一次归并（low到mid有序，mid到high有序）
    private static void merge(int[] array, int low, int mid, int high) {
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

    private static void merge_sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge_sort(array, low, mid);
            merge_sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    //归并排序
    private static void merge_sort(int[] array) {
        merge_sort(array, 0, array.length - 1);
    }
}
