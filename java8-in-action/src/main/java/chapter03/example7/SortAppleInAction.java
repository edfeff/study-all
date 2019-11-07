package chapter03.example7;

import chapter01.Java8Style;
import chapter01.example1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangpp
 */
public class SortAppleInAction {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(100, "a"), new Apple(10, "b"), new Apple(1, "c"));
        System.out.println(apples);
        sortAppleByColor(apples);
        System.out.println(apples);
        sortAppleByWeight(apples);
        System.out.println(apples);
    }

    @Java8Style( "一行代码对Apple进行排序，有点类似策略模式" )
    public static void sortAppleByColor(List<Apple> list) {
        list.sort(Comparator.comparing(Apple::getColor));
    }

    public static void sortAppleByWeight(List<Apple> list) {
        list.sort(Comparator.comparing(Apple::getWeight));
    }

}
