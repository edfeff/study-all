package com.wpp.oauth2.iotdev.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Map;

//@Configuration
public class LoginConfig {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(aaaClientRegistration());
    }

    private ClientRegistration aaaClientRegistration() {
        return ClientRegistration.withRegistrationId("iotdev")  // (1)
                .clientId("c2")  // (2)
                .clientSecret("c1")  // (3)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)  // (4)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")  // (5)
                .clientName("AAA")       // (6)
                .tokenUri("http://localhost:53020/uaa/oauth/token")  // (7)
                .authorizationUri("http://localhost:53020/uaa/oauth/authorize")  // (8)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)  // (9)
                .scope("api")  // (10)
                .userNameAttributeName("username")  // (11)
                .userInfoUri("http://localhost:53020/uaa/user")  // (12)
                .jwkSetUri("")  // (13)
                .build();
    }
}
