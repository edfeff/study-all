package chapter05.example5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 生成勾股数
 *
 * @author wangpp
 */
public class MathExample {
    public static void main(String[] args) {
        firstMethod();
        secondMethod();

    }

    /**
     * 方法2
     * 减少一次开平方
     */
    private static void secondMethod() {
        //必须用double， 因为后过滤的算法，要求上一步不能为整数
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        stream.limit(5).forEach(t -> {
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        });
    }

    /**
     * 方法1
     */
    private static void firstMethod() {
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        stream.limit(5).forEach(t -> {
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        });
    }
}
