package chapter05.example1;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
public class FilterAndDistinct {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        //偶数
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
    }
}
