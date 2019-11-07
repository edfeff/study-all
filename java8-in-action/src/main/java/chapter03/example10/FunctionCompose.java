package chapter03.example10;

import java.util.function.Function;

/**
 * Function compose
 * f(x) g(x)
 * andThen 和 compose 不同在与两个函数的执行顺序是相反的
 * <p>
 * f(x).andThen(g(x))-->  g(f(x))
 * <p>
 * f(x).compose(g(x))  --> f(g(x))
 *
 * @author wangpp
 */
public class FunctionCompose {
    public static void main(String[] args) {
        // x= x+1
        Function<Integer, Integer> add1 = x -> x + 1;
        // x = x*2
        Function<Integer, Integer> doubleX = x -> x * 2;
        // x = （x*2）+1
        Function<Integer, Integer> doubleAdd1 = add1.compose(doubleX);

        // 1+1=2
        System.out.println(add1.apply(1));
        //1*2=2
        System.out.println(doubleX.apply(1));
        //（1*2）+1=3
        System.out.println(doubleAdd1.apply(1));

    }
}
