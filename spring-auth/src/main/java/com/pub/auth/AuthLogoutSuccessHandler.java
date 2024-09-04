package com.pub.auth;

import com.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Objects;

/**
 * @author lx
 * @data 2023/8/30 16:48
 */
@Component
public class AuthLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {


    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (!Objects.isNull(authentication)) {

            String userName = authentication.getName();
            String userTokensKey = Constant.TOKEN_KEY + userName;

            String tokenValue = (String) redisTemplate.opsForList().leftPop(userTokensKey);

            OAuth2AccessToken token = jwtTokenStore.readAccessToken(tokenValue);

            if (Objects.nonNull(token)) {
                jwtTokenStore.removeAccessToken(token);
            }
            super.handle(request, response, authentication);
        }
    }
}

