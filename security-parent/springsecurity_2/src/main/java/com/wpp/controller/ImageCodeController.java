package com.wpp.controller;

import com.wpp.pojo.ImageCode;
import com.wpp.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangpp
 */
@Controller
public class ImageCodeController {
    @RequestMapping( "/imageCode" )
    public void imageCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = ImageCodeUtil.getImage(80, 32, 4);
        request.getSession().setAttribute("key", imageCode.getCode());
        response.setContentType("image/jpeg");
        try {
            ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
