package chapter05.example1;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
public class FilterAndLimit {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        //前3名
        numbers.stream().sorted().distinct().limit(3).forEach(System.out::println);
    }
}
