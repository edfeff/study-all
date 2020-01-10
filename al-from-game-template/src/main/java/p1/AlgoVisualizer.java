package p1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * @author wangpp
 */
public class AlgoVisualizer {
    private static final int SPEED = 10;
    AlgoFrame frame;
    int width;
    int height;

    public AlgoVisualizer() {
    }

    public AlgoVisualizer(int width, int height, int N) {
        this.width = width;
        this.height = height;
//        TODO 初始化
        this.money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("hello", width, height);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
//            TODO 鼠标事件
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            //TODO 键盘事件
        }
    }

    int[] money;

    private void run() {
        while (true) {
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(SPEED);
//            TODO 算法逻辑
//            加速
            int M = 50;
            for (int j = 0; j < M; j++) {
                for (int i = 0; i < money.length; i++) {
                    if (money[i] > 0) {
                        int k = (int) (Math.random() * money.length);
                        money[k]++;
                        money[i]--;
                    }
                }
            }
        }
    }

    public AlgoFrame getFrame() {
        return frame;
    }

    public void setFrame(AlgoFrame frame) {
        this.frame = frame;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
