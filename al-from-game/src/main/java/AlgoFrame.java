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


    private Circle[] circles;

    public void render(Circle[] circles) {
        this.circles = circles;
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
            if (circles != null) {
                for (Circle circle : circles) {
                    if (circle.isSolid()) {
                        helper.fillCircle(circle.getX(), circle.getY(), circle.getR());
                    } else {
                        helper.stockCircle(circle.getX(), circle.getY(), circle.getR());
                    }
                }
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
