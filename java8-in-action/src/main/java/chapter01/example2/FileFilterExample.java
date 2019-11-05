package chapter01.example2;

import chapter01.Java8Style;
import chapter01.OldStyle;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * 文件过滤器例子
 *
 * @author wangpp
 */
public class FileFilterExample {
    public static void main(String[] args) {
        File base = new File("C:\\");
        System.out.println(Arrays.toString(getHiddenFile(base)));
        System.out.println(Arrays.toString(getHiddenFileNew(base)));
    }

    @OldStyle
    public static File[] getHiddenFile(File file) {
        return file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
    }

    @Java8Style
    public static File[] getHiddenFileNew(File file) {
        return file.listFiles(File::isHidden);
    }
}
