package chapter06.example6;

import chapter04.example1.Dish;
import chapter04.example1.DishSupplier;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分区
 * 按照布尔分成两组
 * Collectors.partitioningBy
 *
 * @author wangpp
 */
public class Partition {
    public static void main(String[] args) {
        List<Dish> dishes = DishSupplier.getDishes(10);
        Map<Boolean, List<Dish>> partitionDishes =
                dishes.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionDishes.get(true));
        System.out.println(partitionDishes.get(false));
    }
}
