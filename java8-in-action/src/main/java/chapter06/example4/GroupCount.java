package chapter06.example4;

import chapter06.pojo.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分组统计
 * //分类计数
 * //分类汇总
 * //分类比较
 *
 * @author wangpp
 */
public class GroupCount {
    public static void main(String[] args) {
        Random random = new Random();
        List<Transaction> transactions = Stream.generate(() -> new Transaction(random.nextBoolean() ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, random.nextInt(100), random.nextBoolean())).limit(10).collect(Collectors.toList());
        //分类计数
        Map<Transaction.Currency, Long> groupCount = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.counting()));
        //分类汇总
        Map<Transaction.Currency, Integer> groupSum = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.summingInt(Transaction::getCount)));
        //分类比较
        Map<Transaction.Currency, Optional<Transaction>> groupMax = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.maxBy(Comparator.comparing(Transaction::getCount))));

        System.out.println(groupCount);
        System.out.println(groupSum);
        System.out.println(groupMax);
    }
}
