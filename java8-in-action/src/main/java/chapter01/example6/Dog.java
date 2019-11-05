package chapter01.example6;

/**
 * @author wangpp
 */
public class Dog implements Live {

    @Override
    public void say() {
        System.out.println("wang wang");
    }

    /**
     * 重写了默认方法
     */
    @Override
    public void run() {
        System.out.println("ha qi ha qi");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.say();
        dog.run();

    }
}
