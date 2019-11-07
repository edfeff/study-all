package chapter03.example10;

import java.util.function.Function;

/**
 * 函数复合
 * Function AndThen
 *
 * @author wangpp
 */
public class FunctionAndThenExample {
    public static void main(String[] args) {
        // x= x+1
        Function<Integer, Integer> add1 = x -> x + 1;
        // x = x*2
        Function<Integer, Integer> doubleX = x -> x * 2;
        // x = (x+1)*2
        Function<Integer, Integer> doubleAdd1 = add1.andThen(doubleX);

        // 1+1=2
        System.out.println(add1.apply(1));
        //1*2=2
        System.out.println(doubleX.apply(1));
        //(1+1)*2=4
        System.out.println(doubleAdd1.apply(1));

    }
}
