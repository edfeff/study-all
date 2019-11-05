package chapter03.example5;

import chapter01.example1.Apple;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造函数引用
 *
 * @author wangpp
 */
public class ConstructionMethodRef {
    public static void main(String[] args) {
        //无参构造
        Supplier<Apple> c1 = () -> new Apple();
        c1 = Apple::new;
        Apple a1 = c1.get();

        //有参构造
        Function<Integer, Apple> c2 = Apple::new;
        c2 = (weight) -> new Apple(weight);
        Apple a2 = c2.apply(100);




    }
}
