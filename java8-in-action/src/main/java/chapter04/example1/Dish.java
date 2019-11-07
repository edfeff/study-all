package chapter04.example1;

import lombok.Data;
import lombok.ToString;

/**
 * @author wangpp
 */
@Data
@ToString
public class Dish {
    private Integer calories;
    private String name;

}
