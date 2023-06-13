import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 根据几率 计算 是否命中
 * @author Administrator
 *
 */
public class RandomUtil {
    public static final int gailv = 10000;//总概率

    /**
     * 根据几率  计算是否生成 
     * @param probability
     * @return
     */
    public static boolean isGenerate(int probability, int gailv) {
        if (probability == 0 || gailv < 0) {
            return false;
        }
        if (gailv == 0) {
            gailv = 1000;
        }
        int random_seed = ThreadLocalRandom.current().nextInt(gailv + 1);
        return probability >= random_seed;
    }

    /**
     *
     * gailv/probability 比率形式
     * @param probability
     * @param gailv
     * @return
     */
    public static boolean isGenerate2(int probability, int gailv) {
        if (probability == 0 || gailv < 0) {
            return false;
        }
        if (probability == gailv) {
            return true;
        }
        if (gailv == 0) {
            gailv = 1;
        }
        int random_seed = ThreadLocalRandom.current().nextInt(probability);
        return random_seed + 1 <= gailv;
    }

    /**
     * 根据几率  计算是否生成 
     * @param probability
     * @return
     */
    public static boolean defaultIsGenerate(int probability) {
        if (probability == 0) {
            return false;
        }
        if (probability < 0) {
            new Exception("非法的概率数据：" + probability + " must between 0 to 10000").printStackTrace();
            return false;
        }
        int random_seed = ThreadLocalRandom.current().nextInt(gailv);
        return probability >= random_seed + 1;
    }

    /**
     *
     * @param value
     * @return [0, value)
     */
    public static int randomValue(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

    /**
     * 从 min 和 max 中间随机一个值
     * @param min
     * @param max
     * @return 包含min max
     */
    public static int randomValue(int min, int max) {
        int temp = max - min;
        temp = ThreadLocalRandom.current().nextInt(temp + 1);
        temp = temp + min;
        return temp;
    }

    /**
     * 从 min 和 max 中间随机一个值
     * @param min
     * @param max
     * @return 包含min max
     */
    public static int randomValue(int min, int max,int seed) {
        int temp = max - min;
        temp = new Random(seed).nextInt(temp + 1);
        temp = temp + min;
        return temp;
    }


    /**
     * 返回在0-maxcout之间产生的随机数时候小于num
     * @param num
     * @return
     */
    public static boolean isGenerateToBoolean(int num, int maxcout) {
        double count = Math.random() * maxcout;
        if (num > count) {
            return true;
        }
        return false;
    }

    /**
     * 返回在0-maxcout之间产生的随机数时候小于num
     * @param num
     * @return
     */
    public static boolean isGenerateToBoolean(double num, int maxcout) {
        double count = Math.random() * maxcout;
        if (num > count) {
            return true;
        }
        return false;
    }

    public static float randomFloatValue(float min, float max) {
        return (float) (Math.random() * (double) (max - min)) + min;
    }

    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static int randomSex() {
        return randomValue(3);
    }

    public static long randomLong(long value) {
        return ThreadLocalRandom.current().nextLong(value);
    }
}
