import java.util.Random;

/**
 * @Author dengxinlong
 * @Date 2019/11/27
 * 吧m个苹果随机分成n份,每份至少一个
 */
public class RandomCount {

    public static int[] randomCount(int m, int n) {
        if(n > m || n <= 0){
            return null;
        }
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = 1;
        }
        int remain = m - n;
        Random random = new Random();
        if(remain == 1){
            temp[random.nextInt(n)] += remain;
            return temp;
        }
        int index = 0;
        while (remain > 0) {
            if (index + 1 >= n) {
                temp[index] += remain;
                break;
            }
            int add = random.nextInt(remain) + 1;
            temp[index++] += add;
            remain -= add;
        }
        return temp;
    }
}
