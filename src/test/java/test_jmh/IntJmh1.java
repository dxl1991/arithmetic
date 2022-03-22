package test_jmh;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * @author YH.L
 * @BenchmarkMode： 测试类型，有以下几种可选：
 * Throughput： 吞吐量，即每秒执行可多少次。
 * AverageTime：平均时间，即每次调用平均耗时。
 * SampleTime： 随机取样。
 * SingleShotTime：顾名思义，只运行一次，一般用于测试冷启动性能。
 * All：
 * @Warmup： 预热，一般为保证测试准确性，要预热几次。
 * @Measurement： 测量。
 * iterations：测试多少轮。
 * time：每轮时长。
 * timeUnit：时间单位。
 * @Fork：JMH fork出指定个数的进程测试。
 * @Threads： 每个测试进程的测试线程数量。
 * @OutputTimeUnit： 测试结果的时间单位。
 * @Benchmark： 标记某个方法进行基准测试，类比JUnit的@Test。
 * @Param： 指定同一个方法的不同参数。
 * @State： 标记某对象在指定Scope内共享，通过@Benchmark标记的方法参数注入，Scope分三种：
 * Benchmark:所有线程共享实例。
 * Group:线程组内共享实例。
 * Thread:线程内共享实例。
 * @Setup： 配置了@State的类专用，类比JUnit的@Setup，在benchmark方法执行前state实例做的操作。
 * @TearDown： 配置了@State的类专用，类比JUnit的@TearDown，在benchmark方法执行后state实例做的操作。
 * @date 2021年07月14日.
 **/
@BenchmarkMode(Mode.Throughput)
@Threads(1)
@Fork(1)
@Warmup(iterations = 5, time = 10)
@OutputTimeUnit(TimeUnit.SECONDS)
public class IntJmh1 {

    //public volatile static ByteBuf byteBuf;
    private static byte[] bytes;

    @Benchmark
    public void test1() {
        //byteBuf.resetReaderIndex();
        //byte[] bytes = new byte[byteBuf.readableBytes()];
        //byteBuf.readBytes(bytes);
        try {
            byte[] bytes1 = new byte[bytes.length];
            System.arraycopy(bytes,0,bytes1,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        bytes = new byte[10];

        Options options = new OptionsBuilder().include(IntJmh1.class.getSimpleName()).forks(1).build();
        try {
            new Runner(options).run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
