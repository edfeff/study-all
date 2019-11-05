package chapter01.example6;

import chapter01.Java8Style;

/**
 * @author wangpp
 */
public interface Live {
    void say();

    @Java8Style( "接口的默认方法，这是java8中非常重要的特性" )
    default void run() {
        System.out.println("run");
    }

}
