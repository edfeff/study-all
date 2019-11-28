package com.wpp.security.distributed.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * OAuth2 服务器
 *
 * @author wangpp
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        //客户端信息服务
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        //token 存储
        services.setTokenStore(tokenStore);
        //token 有效期
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);

        return services;
    }

    /**
     * 配置客户端信息
     * 允许的客户端
     *
     * @param clients
     * @throws Exception
     */
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("c1")
                .secret(new BCryptPasswordEncoder().encode("c1"))
//                资源代码
                .resourceIds("res1")
                //授权类型
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//              授权范围
                .scopes("all")
//                false -需要客户点击授权
                .autoApprove(false)
//                回调地址
                .redirectUris("http://www.baidu.com");
    }


    /**
     * oauth 令牌访问端点
     *
     * @param endpoints
     * @throws Exception
     */
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                密码模式需要
                .authenticationManager(authenticationManager)
//                授权码模式需要
                .authorizationCodeServices(authorizationCodeServices)
//                token管理服务
                .tokenServices(tokenServices())
//                允许post提交
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 令牌短点的安全策略
     *
     * @param security
     * @throws Exception
     */
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
//                oauth/token_key
                .tokenKeyAccess("permitAll()")
//                oauth/check_token
                .checkTokenAccess("permitAll()")
                //允许表单认证
                .allowFormAuthenticationForClients();
    }


}
