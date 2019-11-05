package chapter03.example4;

import chapter01.example1.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 方法引用的3中形式
 *
 * @author wangpp
 */
public class MethodRef {
    public static void main(String[] args) {
        /*
        静态方法
               1 （args） -> ClassName.staticMethod(args)
               ClassName::staticMethod
         */
        new Thread(System.out::println).start();
        /*
        实例方法
        2 (arg0,rest) -> arg0.instanceMethod(rest)
               ClassName::instanceMethod
        * */
        Function<Apple, Integer> appleIntegerFunction = (Apple a) -> a.getWeight();
        appleIntegerFunction = Apple::getWeight;

        /*
         *3 (args) -> expr.instanceMethod(args)
         *  expr::instanceMethod
         */
        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
    }
}
