package chapter05.example4;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
public class ReduceExam {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        //计算元素个数
        System.out.println(integers.stream().count());
        System.out.println(integers.stream().map(e -> 1).reduce(Integer::sum).get());
        //并行计算
        System.out.println(integers.parallelStream().count());

    }
}
