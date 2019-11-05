package chapter03.example6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 构造函数引用作为值，传入map
 *
 * @author wangpp
 */
public class ConstructorRefMap {
    //构造函数引用存储到map中
    static Map<String, Function<Integer, Fruit>> map;

    static {
        map = new HashMap<>();
        map.put("orange", Orange::new);
        map.put("peals", Peals::new);

    }

    public static Fruit getFruit(String name, Integer weight) {
        return map.get(name.toLowerCase()).apply(weight);
    }

    public static void main(String[] args) {

        //使用map
        Fruit orange = getFruit("orange", 100);
        Fruit peals = getFruit("peals", 100);
        System.out.println(orange);
        System.out.println(peals);

    }

}

interface Fruit {
    int getWeight();

    String name();
}

@Data
@AllArgsConstructor
@ToString
class Orange implements Fruit {
    String name = "orange";
    int weight;

    public Orange(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String name() {
        return name;
    }
}

@Data
@AllArgsConstructor
@ToString
class Peals implements Fruit {

    String name = "peals";
    int weight;

    public Peals(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String name() {
        return name;
    }
}