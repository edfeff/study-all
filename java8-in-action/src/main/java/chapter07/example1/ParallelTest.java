package chapter07.example1;

import java.util.function.Function;

/**
 * 性能测试
 *
 * @author wangpp
 */
public class ParallelTest {
    public static void main(String[] args) {
        //基础遍历，中等速度
        System.out.println("Iterative sum done in:" + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
        //需要拆装箱，比较慢
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");
        //基本类型 并行流 ，超快
        System.out.println("Parallel base sum done in: " + measureSumPerf(ParallelStreams::baseParallelSum, 10_000_000) + " msecs");
    }

    /**
     * 这个方法接受一个函数和一个long作为参数。它会对传给方法的long应用函数10次，记录
     * 每次执行的时间（以毫秒为单位），并返回最短的一次执行时间
     *
     * @param adder
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
