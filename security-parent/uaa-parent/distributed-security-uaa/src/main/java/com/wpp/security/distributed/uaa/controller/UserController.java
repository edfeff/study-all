package com.wpp.security.distributed.uaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    TokenStore tokenStore;

    @GetMapping("/user")
    public Map<String, String> user(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        String authorization = request.getHeader("authorization");

        if (authorization == null || StringUtils.isEmpty(authorization)) {
            map.put("username", "noname");
        } else {
            String jwtToken = authorization.substring("Bearer ".length());
            System.out.println(jwtToken);
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(jwtToken);
            Object principal = oAuth2Authentication.getPrincipal();
            map.put("username", principal.toString());
        }
        return map;
    }
}
