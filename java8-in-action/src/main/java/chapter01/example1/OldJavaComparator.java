package chapter01.example1;

import chapter01.Java8Style;
import chapter01.OldStyle;

import java.util.*;

/**
 * 旧时Comparator 与java8 Comparator
 *
 * @author wangpp
 */
public class OldJavaComparator {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(1), new Apple(2), new Apple(3));

        sortAppleJava8Style(apples);
        System.out.println(apples);

        sortAppleOldStyle(apples);
        System.out.println(apples);
    }

    @Java8Style
    public static void sortAppleJava8Style(List<Apple> apples) {
        Collections.sort(apples, Comparator.comparingInt(Apple::getWeight));
    }

    @OldStyle
    public static void sortAppleOldStyle(List<Apple> apples) {
        Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return (a1.getWeight() - a2.getWeight());
            }
        });
    }
}
