import java.util.Random;

/**
 * @Author dengxinlong
 * @Date 2020/5/26 18:37
 * @slogan CODE IS TRUTH
 * 当使用同一随机种子后，不同随机数生成器的相同随机次数随机到的结果是一致的
 */
public class RandomTest {
    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        Random random1 = new Random(seed);
        Random random2 = new Random(seed);
        System.out.println(
                "第一次随机：random1=" + random1.nextInt(1000) + ",random3=" + random2.nextInt(1000));
        System.out.println(
                "第二次随机：random1=" + random1.nextInt(1000) + ",random3=" + random2.nextInt(1000));
        System.out.println(
                "第三次随机：random1=" + random1.nextInt(1000) + ",random3=" + random2.nextInt(1000));
        long mask = (1L << 48) - 1;
        System.out.println(Long.toBinaryString(mask).length());
        double a = Math.random();
        double b = Math.random();
        System.out.println(a + "," + b);
    }
}
