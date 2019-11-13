package chapter06.example6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 质数 分区
 *
 * @author wangpp
 */
public class PrimeExample {

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> booleanListMap = partitionPrime(100);
        System.out.println(booleanListMap);
    }

    /**
     * 质数分组
     *
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrime(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(Collectors.partitioningBy(PrimeExample::isPrime));
    }

    /**
     * 判断质数
     * <p>
     * 从 2 - candidate的平方根
     *
     * @param candidate
     * @return
     */
    public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(candidate)).noneMatch(i -> candidate % i == 0);
    }
}
