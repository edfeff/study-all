package chapter01.example3;

import chapter01.Java8Style;
import chapter01.OldStyle;
import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author wangpp
 */
@Java8Style
public class AppleFilterExampleJava8Style {

    @Java8Style( "使用了Predicate接口 " )
    public static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
            return result;
        }
        return result;
    }

    static boolean isGreenApple(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }


    @Java8Style( "调用自己static的方法引用" )
    public static List<Apple> getGreenApple(List<Apple> apples) {
        return filterApple(apples, AppleFilterExampleJava8Style::isGreenApple);
    }

    @Java8Style( "调用了别的类的static方法，作为方法引用" )
    public static List<Apple> getHeavyApple(List<Apple> apples) {
        return filterApple(apples, Apple::isHeavyApple);
    }

    @Java8Style( "使用lambda 表达式，作为方法传递" )
    public static List<Apple> getRedApple(List<Apple> apples) {
        return filterApple(apples, a -> "red".equalsIgnoreCase(a.getColor()));
    }

    @Java8Style( "使用lambda 表达式，作为方法传递" )
    public static List<Apple> getYellowApple(List<Apple> apples) {
        return filterApple(apples, a -> "yellow".equalsIgnoreCase(a.getColor()));
    }

}
