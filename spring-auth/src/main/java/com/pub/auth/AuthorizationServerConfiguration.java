package com.pub.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @author lx
 * @data 2023/8/17 16:51
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

    @Autowired
    @Qualifier("accessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    /**
     * 配置OAuth2客户端
     * @return
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(clientDetailsService);
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // userDetailsService 使用refresh_token 时需要
        endpoints.userDetailsService(userDetailsService)
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager);
    }

    /**
     * 配置授权码和令牌的安全约束
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 使用单点登陆时必须配置
        /**
         * hasAnyRole：用户拥有方法参数中任意一个角色，如上代码中就是。
         * hasAnyAuthority：用户拥有方法参数中任意一个权限。
         * authenticated：登录用户。
         * anonymous：匿名用户。
         * permitAll：任何人都能请求。
         * denyAll：任何人不能请求。
         * rememberMe：使用记住我方式登录的用户可以请求，以后会讲。
         * fullyAuthenticated：使用记住我方式登录的用户不可请求。
         * hasIpAddress：指定ip地址的用户。
         * not：求反，可用于其他方法的前缀。如.not().hasAnyRole("ANONYMOUS")指没有ANONYMOUS角色的用户。
         * access：使用SpEL表达式
         */
        security.tokenKeyAccess("isAuthenticated()");

    }


    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

}
