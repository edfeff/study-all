package chapter06.example2;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Collectors.joining
 *
 * @author wangpp
 */
public class JoinString {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(new String[]{"a", "B", "c"}).collect(Collectors.joining()));
        System.out.println(Arrays.stream(new String[]{"a", "B", "c"}).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(new String[]{"a", "B", "c"}).collect(Collectors.joining(" ", "{", "}")));

    }
}
