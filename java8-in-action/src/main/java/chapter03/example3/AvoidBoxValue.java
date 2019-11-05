package chapter03.example3;

import chapter01.example1.Apple;

import java.util.function.*;

/**
 * 避免 java的自动拆装箱机制，提交程序效率
 *
 * @author wangpp
 */
public class AvoidBoxValue {
    public static void main(String[] args) {
        //基本类型的判断，不会进行装箱
        IntPredicate intPredicate = value -> value > 100;
        LongPredicate longPredicate = value -> value < 1L;
        DoublePredicate doublePredicate = value -> value > 3.1415926;

//        直接获取int
        ToIntFunction<Apple> toIntFunction = apple -> apple.getWeight();
        ToIntFunction<Object> getHashCode = Object::hashCode;

// 直接消费int
        IntConsumer intConsumer = value -> {
            System.out.println(value - 1);
        };

    }
}
