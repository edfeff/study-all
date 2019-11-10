package chapter05.example6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建流
 * Stream.of
 * Stream.empty
 * Arrays.stream
 * Files.lines
 *
 * @author wangpp
 */
public class CreateStream {
    public static void main(String[] args) {
        fromValue();
        emptyStream();
        fromArray();
        fromFile();
    }

    private static void fromFile() {
        try {
            Stream<String> lines = Files.lines(Paths.get("D:\\study\\java\\test\\parent-all\\java8-in-action\\src\\main\\java\\chapter05\\example6\\data.txt"));
            Integer sum = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                    .map(Integer::valueOf)
                    .distinct()
                    .reduce((x, y) -> x + y).get();
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void emptyStream() {
        Stream<Object> empty = Stream.empty();
    }

    private static void fromArray() {
        Arrays.stream(new int[]{1, 2, 3}).map(x -> x * x).forEach(System.out::println);
    }

    private static void fromValue() {
        Stream<String> a = Stream.of("a", "abc", "Abg");
        a.map(String::toUpperCase).forEach(System.out::println);
    }
}
