package al;

import javax.swing.*;
import java.awt.*;

/**
 * 算法GUI框架模板
 *
 * @author wangpp
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    public final static int DEFAULT_WIDTH = 500;
    public final static int DEFAULT_HEIGHT = 500;
    private Object data;

    public AlgoFrame(String title) throws HeadlessException {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) throws HeadlessException {
        super(title);
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
//        画板
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
//        调整窗口
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void render(Object data) {
        this.data = data;
        repaint();
    }

    private Render render = Render.NO_OP();

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }

    public static abstract class Render {
        static Render NO_OP() {
            return new Render() {
                @Override
                public void render(Graphics2D g, Object data) {
                }
            };
        }

        public abstract void render(Graphics2D g, Object data);
    }

    public void renderData(Graphics2D g) {
        //TODO 渲染数据
        render.render(g, data);
    }

    /**
     * 内部绘图面板
     */
    private class AlgoCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            doPaintComponent(graphics2D);
        }

        private void doPaintComponent(Graphics2D g) {
            AlgoVisHelper.setAntiAlias(g);
            AlgoVisHelper.setColor(g, AlgoVisHelper.Red);
            AlgoVisHelper.setStoke(g, 1);
            if (data != null) {
                renderData(g);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }
}
