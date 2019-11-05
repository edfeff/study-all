package chapter01.example3;

import chapter01.OldStyle;
import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangpp
 */
@OldStyle
public class AppleFilterExampleOldStyle {

    public static List<Apple> getGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> getHeavyApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() > 150) {
                list.add(apple);
            }
        }
        return list;
    }
}
