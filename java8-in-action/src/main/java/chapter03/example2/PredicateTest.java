package chapter03.example2;

import chapter01.Java8Style;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author wangpp
 */
public class PredicateTest {
    @Java8Style( "使用 Predicate 接口" )
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("", "a", "b", "c", "", "");

        Predicate<String> notEmpty = s -> !s.isEmpty();
        List<String> notEmptyList = filter(strings, notEmpty);
        System.out.println(notEmptyList);
    }
}
