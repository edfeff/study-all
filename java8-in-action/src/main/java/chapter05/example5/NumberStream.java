package chapter05.example5;

import chapter04.example1.Dish;
import chapter04.example1.DishSupplier;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流
 * 针对原始类型
 * IntStream
 * DoubleStream
 * LongStream
 * <p>
 * OptionalInt
 * OptionalDouble
 * OptionalLong
 *
 * @author wangpp
 */
public class NumberStream {
    public static void main(String[] args) {
        //转成基本数据类型流
        IntStream intStream = streamMapToIntStream();
        OptionalInt max = intStream.max();
        System.out.println(max.getAsInt());
//        转回对象流
        Stream<Integer> integerStream = intStream.boxed();


    }

    /**
     * 映射到 数值流
     * mapToInt
     * mapToDouble
     * mapToLong
     */
    private static IntStream streamMapToIntStream() {
        List<Dish> dishes = DishSupplier.getDishes(10);
        return dishes.stream().mapToInt(Dish::getCalories);
    }

}
