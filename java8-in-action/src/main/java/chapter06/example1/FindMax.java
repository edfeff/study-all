package chapter06.example1;

import chapter06.pojo.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors.maxBy
 *
 * @author wangpp
 */
public class FindMax {
    public static void main(String[] args) {
        List<Transaction> transactions = Stream.generate(() -> new Transaction(Math.random() > 0.5 ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, (int) (100 * Math.random()))).limit(10).collect(Collectors.toList());
//        transactions.stream().forEach(System.out::println);
        Optional<Transaction> max1 = transactions.stream().max(Comparator.comparing(Transaction::getCount));
        Optional<Transaction> max2 = transactions.stream().collect(Collectors.maxBy(Comparator.comparing(Transaction::getCount)));

        System.out.println(max1.get());
        System.out.println(max2.get());

    }
}
