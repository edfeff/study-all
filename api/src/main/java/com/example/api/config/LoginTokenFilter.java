package com.example.api.config;

import com.example.api.pojo.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangpp
 */
@Component
public class LoginTokenFilter extends OncePerRequestFilter {
    private String loginPath = "/api/private/v1/login";
    @Value( "${filter.skipUrl}" )
    private Set<String> skipUrls;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getMethod());
        System.out.println("header" + token);

        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getParameter("token");
//            httpServletRequest.getParameterMap().put("token", new String[]{token});
        }
        if ("options".equalsIgnoreCase(httpServletRequest.getMethod())
                || shouldSkipUrl(httpServletRequest.getRequestURI())
                || Token.token.equals(token)
        ) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().println("{\"meta\":{\"status\":401,\"msg\":\"fail\"},\"data\":\"请登录\"}");
        }
    }

    public boolean shouldSkipUrl(String url) {
        return skipUrls.contains(url);
    }
}
