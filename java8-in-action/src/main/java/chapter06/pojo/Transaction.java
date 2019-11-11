package chapter06.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangpp
 */
@Data
@AllArgsConstructor
public class Transaction {
    public enum Currency {
        Rmb,
        Dollar
    }

    public Transaction(Currency currency, Integer count) {
        this.currency = currency;
        this.count = count;
    }

    private Currency currency;
    private Integer count;
    private boolean isOld;
}
