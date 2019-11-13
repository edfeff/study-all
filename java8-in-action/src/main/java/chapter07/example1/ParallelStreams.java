package chapter07.example1;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author wangpp
 */
public class ParallelStreams {
    public static Long iterativeSum(Long aLong) {
        Long sum = 0L;
        for (int i = 1; i < aLong; i++) {
            sum += i;
        }
        return sum;
    }

    public static Long parallelSum(Long aLong) {
        return Stream.iterate(1L, i -> 1L + i)
                .limit(aLong)
                .parallel()//并行流
                .reduce(Long::sum).get();
    }

    /**
     * 基本类型
     *
     * @param aLong
     * @return
     */
    public static long baseParallelSum(long aLong) {
        return LongStream.rangeClosed(0, aLong).reduce(Long::sum).getAsLong();
    }
}
