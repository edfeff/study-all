package chapter05.example2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的扁平化
 *
 * @author wangpp
 */
public class FlatMap {
    public static void main(String[] args) {
        String[] arr = {"Goodbye", "World"};
        //从数据抽出流
        Stream<String> stream = Arrays.stream(arr);
        //切成数组
        List<String> collect = stream.map(w -> w.split(""))
                //扁平化
                .flatMap(Arrays::stream)
                //去重
                .distinct()
                //收集
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
