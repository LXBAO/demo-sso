package com.pub.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author lx
 * @data 2023/8/18 9:24
 * 资源验证cfg
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                //其他所有请求都需要认证访问
                .authenticated()
                .and()
                .requestMatchers()
                //允许登录页面匿名访问
                .antMatchers("/user/**");
    }

    /**
     *  客户端(资源id) 和服务端进行资源验证 oauth_client_details 表的resourceIds字段进行匹配
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("dev");
        resources.resourceId("test");
    }
}
