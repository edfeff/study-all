import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author wangpp
 */
public class AlgoVisualizer {
    AlgoFrame frame;
    Circle[] circles;
    int width;
    int height;
    int N;
    //    内部碰撞检测
    boolean interCheck = false;
    //    动画
    boolean isAnimated = true;

    public AlgoVisualizer() {
    }

    public AlgoVisualizer(int width, int height, int N) {
        this.width = width;
        this.height = height;
        this.N = N;
        int r = 50;

        circles = new Circle[N];
        for (int i = 0; i < circles.length; i++) {
            int x = (int) (Math.random() * (width - 2 * r) + r);
            int y = (int) (Math.random() * (height - 2 * r) + r);
            int vx = (int) (Math.random() * 11 - 5);
            int vy = (int) (Math.random() * 11 + 5);
            circles[i] = new Circle(x, y, r, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("hello", width, height);
//            添加键盘事件
            frame.addKeyListener(new AlgoKeyListener());
//    鼠标
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
            for (Circle circle : circles) {
                if (circle.contain(e.getPoint())) {
                    circle.setSolid(!circle.isSolid());
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private void run() {
        while (true) {
            frame.render(circles);
            AlgoVisHelper.pause(20);
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, width, 0, height);
                }
                //内部碰撞检测
                if (interCheck) {
                    interalCheck();
                }
            }
        }
    }

    private void interalCheck() {
        for (int i = 0; i < circles.length - 1; i++) {
            for (int j = i + 1; j < circles.length; j++) {
                if (Circle.isCollsion(circles[i], circles[j])) {
                    //System.out.println(i + ":" + j + " 碰撞");
                    circles[i].setVx(-circles[i].getVx());
                    circles[i].setVy(-circles[i].getVy());
                    circles[j].setVx(-circles[j].getVx());
                    circles[j].setVy(-circles[j].getVy());
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

    public Circle[] getCircles() {
        return circles;
    }

    public void setCircles(Circle[] circles) {
        this.circles = circles;
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

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public boolean isInterCheck() {
        return interCheck;
    }

    public void setInterCheck(boolean interCheck) {
        this.interCheck = interCheck;
    }
}
