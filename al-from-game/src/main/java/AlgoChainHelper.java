import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @author wangpp
 */
public class AlgoChainHelper {
    Graphics2D g;

    public AlgoChainHelper(Graphics2D g) {
        this.g = g;
    }

    public AlgoChainHelper setColor(Color color) {
        g.setColor(color);
        return this;
    }

    public AlgoChainHelper setAntiAlias() {
//        开启抗锯齿
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        return this;
    }

    public AlgoChainHelper setStoke(int w) {
        g.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        return this;
    }

    public AlgoChainHelper stockCircle(int x, int y, int r) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.draw(ellipse2D);
        return this;
    }

    public AlgoChainHelper fillCircle(int x, int y, int r) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.fill(ellipse2D);
        return this;
    }

    public Graphics2D getG() {
        return g;
    }

    public void setG(Graphics2D g) {
        this.g = g;
    }

}
