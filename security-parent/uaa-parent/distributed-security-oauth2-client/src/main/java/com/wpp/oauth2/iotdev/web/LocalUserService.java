package com.wpp.oauth2.iotdev.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocalUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {


    @Autowired
    BeanConfiguration.JwtAccessTokenConverterExt converter;

    /**
     * 本地从toke中抽取出数据
     *
     * @param userRequest
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> userAttributes = new HashMap<>();
        String username = "username";

        List<SimpleGrantedAuthority> scopeUser = Arrays.asList(new SimpleGrantedAuthority("SCOPE_USER"));

        OAuth2AccessToken accessToken = userRequest.getAccessToken();

        OAuth2Authentication oAuth2Authentication = converter.readAuthentication(accessToken.getTokenValue());

        userAttributes.put(username, oAuth2Authentication.getPrincipal());
        userAttributes.putAll(userRequest.getAdditionalParameters());

        DefaultOAuth2User user = new DefaultOAuth2User(scopeUser, userAttributes, username);
        return user;
    }

}
