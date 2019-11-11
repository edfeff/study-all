package chapter06.example4;

import chapter06.pojo.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


/**
 * 多级分组
 * Collectors.groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
 *
 * @author wangpp
 */
public class MultiLevelGroup {
    public static void main(String[] args) {
        Random random = new Random();
        List<Transaction> transactions = Stream.generate(() -> new Transaction(random.nextBoolean() ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, random.nextInt(100), random.nextBoolean())).limit(10).collect(Collectors.toList());

        //一级分组
        Map<Transaction.Currency, List<Transaction>> collect1 =
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
        //二级分组
        Map<Transaction.Currency, Map<String, List<Transaction>>> collect2 = transactions.stream().collect(
                Collectors.groupingBy(
                        Transaction::getCurrency,
                        Collectors.groupingBy(
                                t -> {
                                    if (t.getCount() < 50) {
                                        return "little";
                                    } else {
                                        return "much";
                                    }
                                }))
        );
        System.out.println(collect2);

        //三级分组
        Function<Transaction, String> groupByCount = t -> {
            if (t.getCount() < 50) {
                return "little";
            } else {
                return "much";
            }
        };

        Map<Boolean, Map<Transaction.Currency, Map<String, List<Transaction>>>> collect3 = transactions.stream().collect(
                groupingBy(Transaction::isOld,
                        groupingBy(Transaction::getCurrency,
                                groupingBy(groupByCount)))
        );
        System.out.println(collect3);


    }

}
