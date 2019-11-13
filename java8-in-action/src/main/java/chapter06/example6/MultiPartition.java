package chapter06.example6;

import chapter04.example1.Dish;
import chapter04.example1.DishSupplier;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * 多级分区
 *
 * @author wangpp
 */
public class MultiPartition {
    public static void main(String[] args) {
        List<Dish> dishes = DishSupplier.getDishes(10);

        //多级分区
        Map<Boolean, Map<Dish.Type, List<Dish>>> multiPartitionDishes =
                dishes.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)
                        ));
        System.out.println(multiPartitionDishes);

        //分区统计最大热量的dish
        Map<Boolean, Dish> collect = dishes.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect);
    }
}
