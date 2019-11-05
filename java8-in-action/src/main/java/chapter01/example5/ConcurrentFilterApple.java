package chapter01.example5;

import chapter01.Java8Style;
import chapter01.example1.Apple;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 并发Stream
 *
 * @author wangpp
 */
public class ConcurrentFilterApple {
    @Java8Style( "过滤并收集，单线程" )
    public List<Apple> filterApple(List<Apple> list) {
        return list.stream().filter(Apple::isHeavyApple).collect(Collectors.toList());
    }

    @Java8Style( "过滤并收集，多线程" )
    public List<Apple> filterAppleConcurrent(List<Apple> list) {
        return list.parallelStream().filter(Apple::isHeavyApple).collect(Collectors.toList());
    }

}
