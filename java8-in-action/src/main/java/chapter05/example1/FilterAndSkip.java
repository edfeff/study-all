package chapter05.example1;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
public class FilterAndSkip {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(80, 10, 1, 2, 1, 3, 3, 2, 4);
        //跳过前面2个元素，
        numbers.stream().skip(2).sorted().distinct().limit(3).forEach(System.out::println);
        //全部跳过,就是个空
        numbers.stream().skip(numbers.size()).sorted().distinct().limit(3).forEach(System.out::println);
    }
}
