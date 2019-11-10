package chapter05.example5;

import java.util.stream.IntStream;

/**
 * 数值范围
 * IntStream.rangeClosed
 *
 * @author wangpp
 */
public class NumberRange {
    public static void main(String[] args) {
        //int数值范围
        //[1,100]
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0);
        //[1,100)
        evenNumbers = IntStream.range(1, 100);

    }
}
