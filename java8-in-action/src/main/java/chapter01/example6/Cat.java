package chapter01.example6;

/**
 * @author wangpp
 */
public class Cat implements Live {
    @Override
    public void say() {
        System.out.println("miao miao");
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.say();
        //调用了默认方法
        cat.run();
    }
}
