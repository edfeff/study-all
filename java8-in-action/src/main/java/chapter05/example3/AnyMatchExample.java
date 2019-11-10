package chapter05.example3;

import java.util.Arrays;
import java.util.List;

/**
 * 查找 和 匹配
 * 终端操作
 * anyMatch
 * <p>
 * allMatch
 * <p>
 * noneMatch
 *
 * @author wangpp
 */
public class AnyMatchExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 6, 7, 8);

        if (integers.stream().anyMatch(AnyMatchExample::isEven)) {
            System.out.println("有偶数");
        } else {
            System.out.println("没有偶数");
        }

        if (integers.stream().allMatch(AnyMatchExample::isEven)) {
            System.out.println("全部偶数");
        } else {
            System.out.println("不是全部偶数");
        }

        if (integers.stream().noneMatch(AnyMatchExample::isEven)) {
            System.out.println("全不是偶数");
        } else {
            System.out.println("还是有偶数的");
        }

    }

    public static boolean isEven(Integer value) {
        return value % 2 == 0;
    }
}
