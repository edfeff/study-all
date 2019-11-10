package chapter05.example3;

import java.util.Arrays;
import java.util.List;

/**
 * 找第一个匹配元素
 *findFirst
 * @author wangpp
 */
public class FindFirstItemExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        integers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst().ifPresent(System.out::println);
    }
}
