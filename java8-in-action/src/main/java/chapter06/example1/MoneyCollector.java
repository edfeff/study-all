package chapter06.example1;

import chapter06.pojo.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 按照枚举分类收集
 * Collectors.groupingBy
 *
 * @author wangpp
 */
public class MoneyCollector {
    public static void main(String[] args) {
        Map<Transaction.Currency, List<Transaction>> collect = Stream.generate(() -> new Transaction(Math.random() > 0.5 ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, 100))
                .limit(10)
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(collect.get(Transaction.Currency.Rmb));
        System.out.println(collect.get(Transaction.Currency.Dollar));


    }
}
