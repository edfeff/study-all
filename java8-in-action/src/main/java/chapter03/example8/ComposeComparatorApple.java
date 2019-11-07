package chapter03.example8;

import chapter01.example1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器复合
 * 1. 逆序
 * 2. 比较器链
 *
 * @author wangpp
 */
public class ComposeComparatorApple {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(100, "a"), new Apple(10, "b"), new Apple(1, "c"));
//升序
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
//        降序
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(apples);

//    比较器链
//        颜色逆序后大小升序
        apples.sort(Comparator.comparing(Apple::getColor)
                .reversed()
                .thenComparing(Apple::getWeight)
        );
        System.out.println(apples);


    }
}
