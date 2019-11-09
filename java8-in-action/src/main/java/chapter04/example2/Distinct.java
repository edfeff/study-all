package chapter04.example2;

import java.util.Arrays;
import java.util.List;

/**
 * distinct 去重的流
 *
 * @author wangpp
 */
public class Distinct {
    public static void main(String[] args) {
//        找出比1大的，不包含重复元素
        List<Integer> integers = Arrays.asList(1, 1, 2, 2, 3, 4, 4, 5, 5, 6);
        long count = integers.stream().filter(i -> i > 1).distinct().count();
        System.out.println(count);
    }
}
