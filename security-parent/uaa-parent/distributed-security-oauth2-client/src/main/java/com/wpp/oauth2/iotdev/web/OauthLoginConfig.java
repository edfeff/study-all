package com.wpp.oauth2.iotdev.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.util.Map;

@Configuration
public class OauthLoginConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LocalUserService localUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.oauth2Login().userInfoEndpoint().userService(localUserService);

//        配置 OAuth2AuthorizationRequestRedirectFilter的选项
//        认证点解析器
//        http.oauth2Login().authorizationEndpoint().authorizationRequestResolver(null);
//        认证请求存储的仓库
//        http.oauth2Login().authorizationEndpoint().authorizationRequestRepository(null);
//        认证点基础uri
//        http.oauth2Login().authorizationEndpoint().baseUri("");

    }

    public static void main(String[] args) {
        String baseUri = "/oauth2/authorization";
        String registerId = "registerId";
        String pattern = baseUri + "/{" + registerId + "}";
        //AntMatcher使用
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setTrimTokens(false);
        antPathMatcher.setCaseSensitive(true);
        Map<String, String> vars = new AntPathMatcher().extractUriTemplateVariables("/oauth2/authorization/{foo}", "/oauth2/authorization/hello");
        System.out.println(vars);


    }
}
