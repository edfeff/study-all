package chapter07.example2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 分支合并框架
 *
 * @author wangpp
 */
public class ForkJoinExample {
    public static void main(String[] args) {
        long l = forkJoinSum(1_000_000);
        System.out.println(l);
    }

    public static long forkJoinSum(long n) {
        long[] nums = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator calculator = new ForkJoinSumCalculator(nums);
        return new ForkJoinPool().invoke(calculator);
    }
}

class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int len = end - start;
        if (len < THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start, start + len / 2);
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start + len / 2, end);
        Long rightSum = right.compute();
        Long leftSum = left.compute();
        return rightSum + leftSum;
    }

    /**
     * 不可切分，直接线性执行
     *
     * @return
     */
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}