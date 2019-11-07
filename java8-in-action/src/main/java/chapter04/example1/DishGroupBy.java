package chapter04.example1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream 分组
 *
 * @author wangpp
 */
public class DishGroupBy {
    public static void main(String[] args) {
        List<Dish> dishes = DishSupplier.getDishes(10);
//        按照枚举分组
        Map<Dish.Type, List<Dish>> collect = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect);
    }
}
