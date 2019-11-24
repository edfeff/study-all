package com.wpp.interceptor;

import com.wpp.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangpp
 */
@Component
public class SimpleAuthoritionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDto attribute = (UserDto) request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (request.getRequestURI().contains("login")
                || request.getRequestURI().equals("/")
                || request.getRequestURI().contains("logout")
        ) {
            return true;
        }
        if (attribute == null) {
            writeContent(response, "请登录");
        }
        if (request.getRequestURI().contains("p1") && attribute.getAuthorities().contains("p1")) {
            return true;
        }
        if (request.getRequestURI().contains("p2") && attribute.getAuthorities().contains("p2")) {
            return true;
        }
        writeContent(response, "没有权限");
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
        response.resetBuffer();
    }
}
