package chapter03.example9;

import chapter01.example1.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 谓词复合
 * negate  and or
 *
 * @author wangpp
 */
public class ComposePredicateApple {
    public static void main(String[] args) {
        Predicate<Apple> redApple = apple -> "red".equalsIgnoreCase(apple.getColor());
        Predicate<Apple> greenApple = apple -> "green".equalsIgnoreCase(apple.getColor());
        Predicate<Apple> yellowApple = apple -> "yellow".equalsIgnoreCase(apple.getColor());
        Predicate<Apple> bigApple = apple -> 100 < apple.getWeight();

        //不红的苹果
        Predicate<Apple> notRedApple = redApple.negate();
//        大红苹果
        Predicate<Apple> bigRedApple = bigApple.and(redApple);
//        小红苹果
        Predicate<Apple> smallRedApple = redApple.and(bigApple.negate());
//        小不红的苹果
        Predicate<Apple> smallNotRedApple = redApple.negate().and(bigApple.negate());
//       红的或者大的苹果
        Predicate<Apple> redOrBigApple = redApple.or(bigApple);

        List<Apple> apples = Arrays.asList(
                new Apple(100, "red"),
                new Apple(100, "green"),
                new Apple(100, "yellow"),
                new Apple(200, "red"),
                new Apple(200, "green"),
                new Apple(200, "yellow"));

        System.out.println(getSpecifyApple(apples, notRedApple));
        System.out.println(getSpecifyApple(apples, bigRedApple));
        System.out.println(getSpecifyApple(apples, smallRedApple));
        System.out.println(getSpecifyApple(apples, smallNotRedApple));
        System.out.println(getSpecifyApple(apples, redOrBigApple));

    }

    public static List<Apple> getSpecifyApple(List<Apple> apples, Predicate<Apple> condition) {
        return apples.stream().filter(condition).collect(Collectors.toList());
    }
}
