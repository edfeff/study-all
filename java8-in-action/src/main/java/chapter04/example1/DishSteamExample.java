package chapter04.example1;

import chapter01.Java8Style;
import chapter01.OldStyle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream 菜单例子
 * @author wangpp
 */
public class DishSteamExample {
    public static void main(String[] args) {
        List<Dish> dishes = DishSupplier.getDishes(10);
        //低卡菜单
        System.out.println(lowCaloriesMenu(dishes));
        System.out.println(lowCaloriesMenuOldStyle(dishes));
    }

    @Java8Style
    public static List<String> lowCaloriesMenu(List<Dish> dishes) {
        List<String> lowCaloriesMenu = dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(dish -> dish.getCalories() + ":" + dish.getName())
                .collect(Collectors.toList());
        return lowCaloriesMenu;
    }

    @OldStyle
    public static List<String> lowCaloriesMenuOldStyle(List<Dish> dishes) {
        List<Dish> tempMenu = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                tempMenu.add(dish);
            }
        }
        tempMenu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });
        List<String> menu = new ArrayList<>();
        for (Dish dish : tempMenu) {
            menu.add(dish.getCalories() + ":" + dish.getName());
        }
        return menu;
    }
}
