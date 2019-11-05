package chapter03.example2;

import chapter01.Java8Style;
import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author wangpp
 */
public class BiFunctionTest {

    @Java8Style( "使用 BiFunction" )
    public static <T, U, R> List<R> change(List<T> w, List<U> c, BiFunction<T, U, R> biFunction) {
        if (w.size() != c.size()) {
            return null;
        }
        List<R> result = new ArrayList<>();
        for (int i = 0; i < w.size(); i++) {
            R r = biFunction.apply(w.get(i), c.get(i));
            result.add(r);
        }
        return result;

    }

    public static void main(String[] args) {
        List<Integer> weight = Arrays.asList(1, 2, 3);
        List<String> color = Arrays.asList("red", "green", "yellow");
        BiFunction<Integer, String, Apple> makeApple = Apple::new;

        List<Apple> apples = change(weight, color, makeApple);
        System.out.println(apples);


    }
}
