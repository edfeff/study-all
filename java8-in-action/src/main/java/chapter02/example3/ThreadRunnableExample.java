package chapter02.example3;

/**
 * @author wangpp
 */
public class ThreadRunnableExample {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("lambda runnable");
        }).start();
    }
}
