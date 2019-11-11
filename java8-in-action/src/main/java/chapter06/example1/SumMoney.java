package chapter06.example1;

import chapter06.pojo.Transaction;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors.summingInt
 *
 * @author wangpp
 */
public class SumMoney {
    public static void main(String[] args) {
        Integer sum = Stream.generate(() -> new Transaction(Math.random() > 0.5 ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, (int) (100 * Math.random())))
                .limit(10)
                .collect(Collectors.summingInt(Transaction::getCount));
        System.out.println(sum);


    }
}
