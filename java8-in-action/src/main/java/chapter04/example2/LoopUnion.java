package chapter04.example2;

import chapter04.example1.Dish;
import chapter04.example1.DishSupplier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 会合并中间操作的循环，短路特性展示
 *
 * @author wangpp
 */
public class LoopUnion {
    public static void main(String[] args) {
        List<Dish> dishes = DishSupplier.getDishes(15);

        //没有终端操作时，流是不会运行的
        System.out.println("开始  1");
        Stream<String> dishStream = dishes.stream().filter(dish -> {
            System.out.println("filter: " + dish.getName());
            return dish.getCalories() < 400;
        }).map(dish -> {
            System.out.println("map: " + dish.getName());
            return dish.getName();
        }).limit(3);

        System.out.println("开始  2 ");
        dishStream.collect(Collectors.toList());
        System.out.println("结束 ");
        /**
         * 开始  1
         * 开始  2        没有中间操作时，Stream流不会运行
         * filter: dish-1
         * filter: dish-2
         * filter: dish-3
         * map: dish-3   循环发生了合并
         * filter: dish-4
         * map: dish-4
         * filter: dish-5
         * filter: dish-6
         * filter: dish-7
         * filter: dish-8
         * map: dish-8   只出现3个map，是因为limit的短路特性
         * 结束
         */
    }
}
