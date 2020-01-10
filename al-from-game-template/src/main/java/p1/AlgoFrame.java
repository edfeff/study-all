package p1;

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

    private int[] money;

    public void render(int[] money) {
        this.money = money;
        repaint();
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
            AlgoChainHelper helper = new AlgoChainHelper(g);
            helper.setAntiAlias()
                    .setColor(Color.RED)
                    .setStoke(1);
            if (money != null) {
                renderData(g);
            }
        }

        private void renderData(Graphics2D g) {
            //TODO 渲染数据
            int w = canvasWidth / money.length;
            AlgoVisHelper.setColor(g, AlgoVisHelper.Green);
            for (int i = 0; i < money.length; i++) {
                AlgoVisHelper.fillRectangle(g, w * i + 1, canvasHeight - money[i], w - 1, money[i]);
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
