package chapter05.example3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找元素
 * findAny
 * Optional
 *
 * @author wangpp
 */
public class FindExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> anyIgt1 = integers.stream().filter(i -> i > 1).findAny();
        if (anyIgt1.isPresent()) {
            System.out.println("找到大于1的数字了：" + anyIgt1.get());
        }


        //如果存在则直接执行
        integers.stream().filter(i -> i > 3).findAny().ifPresent(System.out::println);
        //不存在时，使用默认值
        System.out.println(integers.stream().filter(i -> i > 4).findAny().orElse(-1));


    }
}
