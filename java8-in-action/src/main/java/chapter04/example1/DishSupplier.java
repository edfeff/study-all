package chapter04.example1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 随机菜
 *
 * @author wangpp
 */
public class DishSupplier {
    private static final Random random = new Random();
    private static final AtomicInteger numbers = new AtomicInteger(1);
    private static final Supplier<Dish> dishSupplier = () -> {
        Dish dish = new Dish();
        dish.setCalories(random.nextInt(1000));
        dish.setName("dish-" + numbers.getAndIncrement());
        Dish.Type type = Dish.Type.values()[random.nextInt(Dish.Type.values().length - 1)];
        dish.setType(type);
        return dish;
    };

    public static List<Dish> getDishes(int count) {
        return Stream.generate(dishSupplier).limit(count).collect(Collectors.toList());
    }
}
