package chapter02.example2;

import chapter01.example1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 比较示例
 *
 * @author wangpp
 */
public class CompareExample {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(1), new Apple(2), new Apple(3));
        apples.sort(Comparator.comparingInt(Apple::getWeight));
        System.out.println(apples);
        apples.sort((a, b) -> b.getWeight() - a.getWeight());
        System.out.println(apples);
    }

}
