package al;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author wangpp
 */
public class AlgoVisHelper {
    private AlgoVisHelper() {
    }

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9c27b0);
    public static final Color DeepPurple = new Color(0x673ab7);
    public static final Color Inigo = new Color(0x3F51b5);
    public static final Color Blue = new Color(0x2196f3);
    public static final Color LigntBlue = new Color(0x03a9f4);
    public static final Color Cyan = new Color(0x00bcd4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4caf50);
    public static final Color LightGreen = new Color(0x8ba34a);
    public static final Color Lime = new Color(0xcddc39);
    public static final Color Yellow = new Color(0xffeb3b);
    public static final Color Amber = new Color(0xffc107);
    public static final Color Orange = new Color(0xff9800);
    public static final Color DeepOrange = new Color(0xff5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9e9e9e);
    public static final Color BlueGrey = new Color(0x607d8b);
    public static final Color Black = new Color(0x0);
    public static final Color White = new Color(0xFFFFFF);

    public static void setAntiAlias(Graphics2D g) {
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
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

    public static void fillRectangle(Graphics2D g, int x, int y, int w, int h) {
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
        g.fill(rectangle2D);
    }

    public static void stockRectangle(Graphics2D g, int x, int y, int w, int h) {
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
        g.draw(rectangle2D);
    }

    public static void putImage(Graphics2D g, int x, int y, String imageURL) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();
        g.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g, String text, int centerX, int centerY) {
        if (text == null) {
            throw new IllegalArgumentException("text is null");
        }
        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g.drawString(text, centerX - w / 2, centerY + h);
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
