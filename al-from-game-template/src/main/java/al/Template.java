package al;

import java.awt.*;

/**
 * @author wangpp
 */
public class Template {
    public static void main(String[] args) {
        int width = 500;
        int height = 500;
        AlgoFrame frame = new AlgoFrame("hello", width, height);
        frame.setRender(new AlgoFrame.Render() {
            @Override
            public void render(Graphics2D g, Object data) {

            }
        });
//        AlgoVisualizer algoVisualizer = new AlgoVisualizer(width, height);
    }
}
