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

    private Currency currency;
    private Integer count;
}
