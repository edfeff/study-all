package chapter06.example1;

import chapter06.pojo.Transaction;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors.summarizingInt
 * summarizingDouble
 * summarizingLong
 *
 * @author wangpp
 */
public class SummarizingMoney {
    public static void main(String[] args) {
        IntSummaryStatistics collect = Stream.generate(() -> new Transaction(Math.random() > 0.5 ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, (int) (100 * Math.random()))).limit(10)
                .collect(Collectors.summarizingInt(Transaction::getCount));
        System.out.println("average:" + collect.getAverage());
        System.out.println("count:" + collect.getCount());
        System.out.println("max:" + collect.getMax());
        System.out.println("min:" + collect.getMin());
        System.out.println("sum:" + collect.getSum());

    }
}
