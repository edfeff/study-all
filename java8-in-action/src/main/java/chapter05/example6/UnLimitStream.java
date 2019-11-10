package chapter05.example6;

import java.util.stream.Stream;

/**
 * 无限流
 * Stream.iterate
 * Stream.generate
 *
 * @author wangpp
 */
public class UnLimitStream {
    public static void main(String[] args) {
        evenStream();
        fiobStream();
        generateStream();

    }

    private static void generateStream() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * 斐波那契数列流
     */
    private static void fiobStream() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        System.out.println("=======");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    private static void evenStream() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

    }
}
