import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author dengxinlong
 * @date 2020/6/15 15:10
 * @version 1.0
 * 四大核心函数式接口：Function、Consumer、Supplier、Predicate
 * @FunctionalInterface 都被该注解修饰，代表是函数式接口
 */
public class FunctionCode {
    public static void main(String[] args) {
//        function();
        consumer();
    }

    /**
     * 提供功能函数
     * Function<T, R> T：入参类型，R：出参类型
     * 调用方法：R apply(T t);
     */
    private static void function(){
        Function<Integer,String> function = p -> p + ",function";
        System.out.println(function.apply(1));
    }
    /**
     * 消费类型函数
     * T：入参类型；没有出参
     * 调用方法：void accept(T t);
     */
    private static void consumer(){
        Consumer<Integer> consumer = p -> System.out.println(p);
        consumer.accept(1);

        consumer = consumer.andThen(p -> System.out.println(p+1));
        consumer.accept(1);
    }
    /**
     * 提供一个结果
     * T：出参类型；没有入参
     * 调用方法：T get();
     */
    private static void supplier(){
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get()); //""
    }
    /**
     * 提供一种判断
     * T：入参类型；出参类型是Boolean
     * 调用方法：boolean test(T t);
     */
    private static void predicate(){
        Predicate<Integer> predicate = p -> p % 2 == 0;
        System.out.println(predicate.test(4)); //true

        predicate = predicate.and(p -> p > 100);
        System.out.println(predicate.test(4)); //false
    }

}
