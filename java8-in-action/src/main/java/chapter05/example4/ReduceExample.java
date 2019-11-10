package chapter05.example4;

import java.util.Arrays;
import java.util.List;

/**
 * 归约 reduce
 *
 * @author wangpp
 */
public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        //元素求和
        System.out.println(integers.stream().reduce((a, b) -> a + b).get());
        System.out.println(integers.stream().reduce(Integer::sum).get());

        //最值
        System.out.println(integers.stream().reduce((a, b) -> a > b ? a : b).get());
        System.out.println(integers.stream().reduce(Integer::max).get());

        System.out.println(integers.stream().reduce((a, b) -> a < b ? a : b).get());
        System.out.println(integers.stream().reduce(Integer::min).get());


    }
}
