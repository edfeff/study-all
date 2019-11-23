package com.wpp.pojo;

import java.awt.image.BufferedImage;

/**
 * @author wangpp
 */
public class ImageCode {
    private String Code;
    private BufferedImage image;

    public ImageCode(String code, BufferedImage image) {
        Code = code;
        this.image = image;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
