package test_jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunhua
 * @create 2022/3/15 17:33
 */
@BenchmarkMode({Mode.AverageTime})
@Threads(1)
@Fork(value = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchForTest {
    static List<Integer> list = new ArrayList<>();
    {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt());
        }
    }
    @Benchmark
    public void benchFor() {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : list) {
            result.add(integer);
        }
    }

    @Benchmark
    public void benchFori() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(i, list.get(i));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(BenchForTest.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

}
