package com.pub.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author lx
 * @data 2023/8/18 14:11
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

    /**
     * 资源ID
     */
    private static final String RESOURCE_ID = "dev";

    /**
     * 资源配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {

        resources.resourceId(RESOURCE_ID)
                .tokenStore(jwtTokenStore)
                .stateless(true);
    }
    /**
     * 请求配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
