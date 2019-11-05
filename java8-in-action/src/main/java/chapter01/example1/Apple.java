package chapter01.example1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wangpp
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private Integer weight;
    private String color;

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
