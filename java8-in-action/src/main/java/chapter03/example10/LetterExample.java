package chapter03.example10;

import java.util.function.Function;

/**
 * 利用函数复合，信件编写小工具
 * @author wangpp
 */
public class LetterExample {
    //加头部
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan:  \n" + text;
    }

    //加脚注
    public static String addFooter(String text) {
        return text + "\nKind regards";
    }

    //拼写纠正
    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }

    public static void main(String[] args) {
        //组合工具
        Function<String, String> addHeader = LetterExample::addHeader;

        //加头部-检查拼写-加脚注
        Function<String, String> transformationPipeline = addHeader
                .andThen(LetterExample::checkSpelling)
                .andThen(LetterExample::addFooter);

        String letter = transformationPipeline.apply("hello wpp, 我在学习labda");
        System.out.println(letter);


    }


}
