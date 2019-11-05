package chapter03.example1;

import chapter01.Java8Style;

import java.io.*;

/**
 * @author wangpp
 */
public class ProcessFile {
    @Java8Style( "使用函数式接口，将处理文件的行为抽象出来" )
    public static String process(File f, BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
            return processor.process(bufferedReader);
        }
    }
}
