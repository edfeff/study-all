package chapter05.example2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 映射
 *
 * @author wangpp
 */
public class MapExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, -2, -1, 3, 3, 2, 4);
        //映射出绝对值
        numbers.stream().map(Math::abs).collect(Collectors.toList()).forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "aaaa", "deafgeg");
        //找出最长的
        strings.stream().map(String::length).sorted(Comparator.comparing(Integer::intValue).reversed()).limit(1).forEach(System.out::println);


    }

}
