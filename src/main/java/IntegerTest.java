import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author dengxinlong
 * @date 2023/9/26 10:29
 */
public class IntegerTest {
    public static void main(String[] args) {
        test3();
    }

    private static void test3(){
//        Long sum = 0L; // 使用包装类相加
//        while (true){
//            for (long i = 0; i < Integer.MAX_VALUE; i++) {
//                sum += i;
//            }
//        }
        long sum = 0L; // 使用基础类型相加
        while (true){
            for (long i = 0; i < Integer.MAX_VALUE; i++) {
                sum += i;
            }
        }
    }

    private static void test2(){
        // 使用流生成0到40000000的List
        List<Long> arr = LongStream.rangeClosed(0, 40000000).boxed().collect(Collectors.toList());
// ①在for中取取变量时为基本类型, 结果存放为基本类型
        long start = System.currentTimeMillis();
        long sum = 0;
        for(long l:arr) { // 临时变量l在栈中创建,直接将arr中的元素转化为LongValue
            sum += l;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)/1000.0);
// ② 在for中取取变量时为包装类型，结果存放为基本类型
        start = System.currentTimeMillis();
        long Sum = 0L;
        for(Long l:arr) { // 临时变量l在堆中创建
            Sum += l; // 相加时转时调用l.longValue()
        }
        System.out.println(Sum);
        end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)/1000.0);
// ③ 在for中取取变量时为包装类型，结果存放为包装类
        start = System.currentTimeMillis();
        Long SUM = 0L;
        for(Long l:arr) {// 临时变量l在堆中创建
            SUM += l; // l和SUM都调用longValue()，完成相加后再装箱
        }
        System.out.println(SUM);
        end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)/1000.0);
    }

    private static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L; // 使用包装类相加
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)/1000.0);

        start = System.currentTimeMillis();
        long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i; // 使用基本数据类型相加
        }
        System.out.println(sum1);
        end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)/1000.0);
    }
}
