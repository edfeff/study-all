package chapter03.example2;

import chapter01.Java8Style;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author wangpp
 */
public class ConsumerTest {
    @Java8Style( "使用了 Consumer 接口" )
    public static <T> void ForEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<Integer> showOddNumber = integer -> {
            if (integer % 2 == 0) {
                System.out.println(integer);
            }
        };
        ForEach(integers, showOddNumber);

    }
}
