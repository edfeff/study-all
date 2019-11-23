package com.wpp.util;

import com.wpp.pojo.ImageCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author wangpp
 */
public class ImageCodeUtil {
    public static ImageCode getImage(int width, int height, int nums) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        String codeHash = Integer.toHexString(random.nextInt());
        String code = codeHash.substring(0, nums);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(code, 8, 24);
        g.dispose();

        ImageCode imageCode = new ImageCode(code, image);
        return imageCode;
    }
}
