package chapter02.example1;

import chapter01.Java8Style;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 行为抽象化
 * 类型抽象化
 *
 * @author wangpp
 */
public class TypeAbstractList {

    @Java8Style( "第一个参数 将类型抽象了，第二个参数 将行为抽象化" )
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : result) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
