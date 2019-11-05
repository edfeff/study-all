package chapter03.example2;

import chapter01.Java8Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author wangpp
 */
public class SupplierTest {
    @Java8Style( "使用 Supplier" )
    public static <T> List<T> getList(int nums, Supplier<T> supplier) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();

        Supplier<Integer> randSupplier = () -> random.nextInt();

        List<Integer> randList = getList(10, randSupplier);
        System.out.println(randList);

        randList = getList(5, () -> random.nextInt(10));
        System.out.println(randList);
    }
}
