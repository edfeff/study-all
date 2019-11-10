package chapter05.example2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * flatmap int数组例子
 *
 * @author wangpp
 */
public class FlatMapExample2 {
    public static void main(String[] args) {
        exam1();
        exam2();
        exam3();

    }

    /**
     * 只能被3整除的数组对
     */
    private static void exam3() {
        List<Integer> arr1 = Arrays.asList(1, 2, 3);
        List<Integer> arr2 = Arrays.asList(3, 4);
        List<int[]> list = arr1.stream()
                .flatMap(i -> arr2.stream()
                        //加入筛选
                        .filter(j -> (i + j) % 3 == 0)

                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        list.forEach(arr -> {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        });
    }

    /**
     * 数组对
     */
    private static void exam2() {
        List<Integer> arr1 = Arrays.asList(1, 2, 3);
        List<Integer> arr2 = Arrays.asList(3, 4);
        List<int[]> list = arr1.stream()
                .flatMap(i -> arr2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        list.forEach(arr -> {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        });
    }

    /**
     * 转成平方
     */
    private static void exam1() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerSquare = integers.stream().map(e -> e * e).collect(Collectors.toList());
    }

}
