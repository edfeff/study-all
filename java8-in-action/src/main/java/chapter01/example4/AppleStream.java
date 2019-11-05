package chapter01.example4;

import chapter01.Java8Style;
import chapter01.OldStyle;
import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangpp
 */
public class AppleStream {
    @Java8Style( "Stream  操作，将大苹果按照颜色分组" )
    public Map<String, List<Apple>> bigAppleGroupByColor(List<Apple> apples) {
        Map<String, List<Apple>> bigAppleGroupByColorMap = apples.stream().filter(Apple::isHeavyApple)
                .collect(Collectors.groupingBy(Apple::getColor));
        return bigAppleGroupByColorMap;
    }

    @OldStyle()
    public Map<String, List<Apple>> bigAppleGroupByColorOld(List<Apple> apples) {
        Map<String, List<Apple>> bigAppleGroupByColorMap = new HashMap<>();
        for (Apple apple : apples) {
            if (Apple.isHeavyApple(apple)) {
                List<Apple> list = bigAppleGroupByColorMap.get(apple.getColor());
                if (list == null) {
                    list = new ArrayList<>();
                    bigAppleGroupByColorMap.put(apple.getColor(), list);
                }
                list.add(apple);
            }
        }
        return bigAppleGroupByColorMap;
    }
}
