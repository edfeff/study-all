import java.awt.*;

/**
 * @author wangpp
 */
public class Circle {
    private int x, y, r, vx, vy;
    private boolean isSolid = false;

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public static boolean isCollsion(Circle c1, Circle c2) {
//        圆心距离平方
        int cDist2 = (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
//        半径和平方
        int rDist2 = (c1.r + c2.r) * (c1.r + c2.r);
        return cDist2 <= rDist2;
    }

    public boolean contain(Point point) {
        return (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y) <= r * r;
    }

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void move(int left, int right, int top, int down) {
        x += vx;
        y += vy;
        if (x - r <= left) {
            x = left + r;
            vx = -vx;
        } else if (x + r >= right) {
            x = right - r;
            vx = -vx;
        }
        if (y - r <= top) {
            y = top + r;
            vy = -vy;
        } else if (y + r >= down) {
            y = down - r;
            vy = -vy;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }


}

