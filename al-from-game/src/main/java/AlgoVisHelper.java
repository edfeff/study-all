import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @author wangpp
 */
public class AlgoVisHelper {
    private AlgoVisHelper() {
    }

    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    public static void setStoke(Graphics2D g, int w) {
        g.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void stockCircle(Graphics2D g, int x, int y, int r) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.draw(ellipse2D);
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.fill(ellipse2D);
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
