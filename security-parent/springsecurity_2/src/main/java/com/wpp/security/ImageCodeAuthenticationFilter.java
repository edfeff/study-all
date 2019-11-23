package com.wpp.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author wangpp
 */
public class ImageCodeAuthenticationFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/login")) {
            try {
                String code = request.getParameter("imageCode");
                if (StringUtils.isEmpty(code)) {
                    throw new UsernameNotFoundException("验证码不能为空");
                }
                String key = (String) request.getSession().getAttribute("key");
                if (!code.equalsIgnoreCase(key)) {
                    throw new ImageCodeException("验证码不一致");
                }
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
