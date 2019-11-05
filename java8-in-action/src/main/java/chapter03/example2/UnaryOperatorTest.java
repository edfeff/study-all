package chapter03.example2;

import chapter01.example1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author wangpp
 */
public class UnaryOperatorTest {
    public static <T> List<T> use(List<T> list, UnaryOperator<T> unaryOperator) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            result.add(unaryOperator.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(1), new Apple(2), new Apple(3));

        //吃苹果
        UnaryOperator<Apple> eatApple = apple -> {
            apple.setWeight(apple.getWeight() - 1);
            return apple;
        };

        use(apples, eatApple);
        System.out.println(apples);

        //染色
        use(apples, apple -> {
            apple.setColor("red");
            return apple;
        });
        System.out.println(apples);

    }
}
