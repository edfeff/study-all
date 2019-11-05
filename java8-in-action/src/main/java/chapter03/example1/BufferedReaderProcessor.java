package chapter03.example1;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 函数式接口
 * 重要的是定义函数的签名 （参数）-> 返回值
 *
 * @author wangpp
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    /**
     * 函数签名声明,lambda只要符合如下签名即可
     * （BufferedReader）->String
     *
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    String process(BufferedReader bufferedReader) throws IOException;
}
