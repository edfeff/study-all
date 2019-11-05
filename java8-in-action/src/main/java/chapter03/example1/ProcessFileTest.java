package chapter03.example1;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 测试函数式接口，使用lambda传入行为
 *
 * @author wangpp
 */
public class ProcessFileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\study\\java\\test\\parent-all\\java8-in-action\\src\\main\\java\\chapter03\\example1\\test.txt");
//        读1行
        String r1 = ProcessFile.process(file, (bufferedReader -> bufferedReader.readLine()));
        System.out.println(r1);

        //读2行
        String r2 = ProcessFile.process(file, (b) -> b.readLine() + b.readLine());
        System.out.println(r2);

    }
}
