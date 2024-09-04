package com.pub.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lx
 * @data 2023/8/18 13:39
 * Security登录信息配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 @Autowired
 private AuthLogoutSuccessHandler authLogoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()// 禁用跨站攻击
                .authorizeRequests()
                //允许登录页面匿名访问
                .antMatchers("/oauth/**", "/login/**", "/static/login.html",
                        "/success.html", "/fail.html")
                .permitAll()
                //其他所有的都要进行验证
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //自定义登录页面
                /* .loginPage("/static/login.html")*/
                //调整url
              /*  .loginPage("/login") */  //  登录页
                .failureUrl("/login-error").permitAll()
                //错误url
                .and()
                .logout()  //添加 /logout 访问点，能退出
                .logoutUrl("/logout")
        .logoutSuccessHandler(authLogoutSuccessHandler)
              /*  .logoutSuccessUrl("/index")*/; //退出后访问
    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
