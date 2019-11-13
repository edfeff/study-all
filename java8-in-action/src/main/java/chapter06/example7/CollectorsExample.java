package chapter06.example7;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Collector 收集器接口
 * 泛型
 * 1 项目的类型
 * 2 累加器的类型
 * 3 收集最后的对象的类型
 *
 * @author wangpp
 */
public class CollectorsExample<T> implements Collector<T, List<T>, List<T>> {
    /**
     * 建立新的结果容器
     * 返回一个空的Supplier
     * 用于创建一个新的累加器实例
     *
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     *
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 对结果容器应用最终转换
     *
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 合并两个结果容器
     * 用于并行归约
     *
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    /**
     * 用于描述此收集器的行为
     * CONCURRENT 可多线程同时调用
     * UNORDERED 结果不受顺序影响
     * IDENTITY_FINISH 可以直接进行转换
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(
                EnumSet.of(
                        Characteristics.CONCURRENT,
                        Characteristics.IDENTITY_FINISH));
    }
}
