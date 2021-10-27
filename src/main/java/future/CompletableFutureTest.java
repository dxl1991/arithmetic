package future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
 * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
 * CompletableFuture可以指定异步处理流程：
 *
 * thenAccept()处理正常结果；
 * exceptional()处理异常结果；
 * thenApplyAsync()用于串行化另一个CompletableFuture；
 * anyOf()和allOf()用于并行化多个CompletableFuture。
 * @author dengxinlong
 * @date 2021/8/10 11:49
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        thenCombine();
    }

    /**
     * 异步执行任务，用的是
     * @throws Exception
     */
    static void test1() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    //CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行
    // 例如，定义两个CompletableFuture，第一个CompletableFuture根据证券名称查询证券代码，
    // 第二个CompletableFuture根据证券代码查询证券价格，这两个CompletableFuture实现串行操作如下
    static void test2() throws Exception{
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() ->
                queryCode("中国石油","https://finance.sina.com.cn/code/")
        );
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) ->
                fetchPrice(code,"https://finance.sina.com.cn/price/")
        );
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    //除了串行执行外，多个CompletableFuture还可以并行执行。例如，我们考虑这样的场景：
    // anyOf()可以实现“任意个CompletableFuture只要一个成功”，allOf()可以实现“所有CompletableFuture都必须成功”，
    // 这些组合操作可以实现非常复杂的异步流程控制。
    static void test3() throws Exception{
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) ->
            fetchPrice((String) code, "https://finance.sina.com.cn/price/")
        );
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) ->
            fetchPrice((String) code, "https://money.163.com/price/")
        );

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept(result -> System.out.println("price: " + result)
        );
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    //实现同步调用效果
    static void test4() throws Exception{
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(()->{
            try {
                //String result = future.get(); //等待回调
                String result = future.get(3000, TimeUnit.MILLISECONDS); //等待回调
                System.out.println("同步等待结果："+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        //future.cancel(false); //如果有线程再等回调结果，这个取消就会抛异常
        //future.completeExceptionally(new Throwable("回调错误")); //抛异常回调
        future.complete("这是回调结果1"); //回调
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    public static void handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }
    private static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "111";
            }
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 123;
            }
        });
        CompletableFuture<Long> result = future1.thenCombine(future2, new BiFunction<String, Integer, Long>() {
            @Override
            public Long apply(String t, Integer u) {
                return Long.valueOf(t) + u;
            }
        });
        System.out.println(result.get());
    }
}
