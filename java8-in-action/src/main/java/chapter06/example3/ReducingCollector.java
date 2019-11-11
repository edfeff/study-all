package chapter06.example3;

import chapter06.pojo.Transaction;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 广义归约收集
 * Collectors.reducing
 *
 * @author wangpp
 */
public class ReducingCollector {
    public static void main(String[] args) {
        List<Transaction> transactions = Stream.generate(() -> new Transaction(Math.random() > 0.5 ? Transaction.Currency.Rmb : Transaction.Currency.Dollar, (int) (100 * Math.random()))).limit(10).collect(Collectors.toList());
        Integer sum = transactions.stream().collect(Collectors.reducing(0, Transaction::getCount, (x, y) -> x + y));
        sum = transactions.stream().collect(Collectors.reducing(
                0, //初始值
                Transaction::getCount, //转换函数
                Integer::sum)); //累积函数
        System.out.println(sum);

        Integer max = transactions.stream().collect(Collectors.reducing(0, Transaction::getCount, (x, y) -> x > y ? x : y));
        max = transactions.stream().collect(Collectors.reducing(0, Transaction::getCount, Integer::max));
        System.out.println(max);

        Integer min = transactions.stream().collect(Collectors.reducing(0, Transaction::getCount, (x, y) -> x < y ? x : y));
        min = transactions.stream().collect(Collectors.reducing(0, Transaction::getCount, Integer::min));
        System.out.println(min);
    }
}
