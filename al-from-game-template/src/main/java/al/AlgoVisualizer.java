package al;

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
    Object data;
    int width;
    int height;
    private int hz = 20;

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AlgoVisualizer() {
    }

    public AlgoVisualizer(int width, int height, AlgoFrame frame, InitHandler handler) {
        this.width = width;
        this.height = height;
//        TODO 初始化
        init();
        EventQueue.invokeLater(() -> {
            this.frame = frame;
            this.frame.addKeyListener(new AlgoKeyListener());
            this.frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private InitHandler handler = InitHandler.NO_OP();

    public InitHandler getHandler() {
        return handler;
    }

    public void setHandler(InitHandler handler) {
        this.handler = handler;
    }

    public static abstract class InitHandler {
        static InitHandler NO_OP() {
            return new InitHandler() {
                @Override
                public void init(AlgoVisualizer algoVisualizer) {
                }

                @Override
                public void mouse(AlgoVisualizer algoVisualizer, MouseEvent event) {
                }

                @Override
                public void key(AlgoVisualizer algoVisualizer, KeyEvent event) {
                }

                @Override
                public void calc(AlgoVisualizer algoVisualizer) {
                }
            };
        }

        public abstract void init(AlgoVisualizer algoVisualizer);

        public abstract void mouse(AlgoVisualizer algoVisualizer, MouseEvent event);

        public abstract void key(AlgoVisualizer algoVisualizer, KeyEvent event);

        public abstract void calc(AlgoVisualizer algoVisualizer);
    }

    private void init() {
        handler.init(this);
    }

    private void mouse(MouseEvent event) {
        handler.mouse(this, event);
    }

    private void key(KeyEvent event) {
        handler.key(this, event);
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
//            TODO 鼠标事件
            mouse(e);
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            //TODO 键盘事件
            key(e);
        }
    }

    private void run() {
        while (true) {
            frame.render(data);
            AlgoVisHelper.pause(hz);
//            TODO 算法逻辑
            handler.calc(this);
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
