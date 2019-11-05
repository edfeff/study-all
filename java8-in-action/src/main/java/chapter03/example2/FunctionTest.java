package chapter03.example2;

import chapter01.Java8Style;
import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author wangpp
 */
public class FunctionTest {
    @Java8Style( "将 T 类型 转成 R 类型" )
    public static <T, R> List<R> map(List<T> source, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : source) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(1), new Apple(2), new Apple(3));

        Function<Apple, Integer> getWeightFunction = apple -> apple.getWeight();
        List<Integer> appleWeightList = map(apples, getWeightFunction);
        System.out.println(appleWeightList);

        List<String> appleColorList = map(apples, Apple::getColor);
        System.out.println(appleColorList);
    }
}
