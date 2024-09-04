package com.common;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.vo.UserInfo;
import com.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @data 2023/8/17 15:18
 */
@Configuration
public class TokenStoreConfig {
    /**
     * 秘钥串
     */
    private static final String SIGNING_KEY = "SigningKey";

    @Resource
    private UserMapper userMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Bean
    public TokenStore jwtTokenStore(RedisConnectionFactory redisConnectionFactory) {

        return new RedisTokenStore(redisConnectionFactory);
    }

    // JWT
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             * 获取token 是调用
             * 重写增强token方法,用于自定义一些token总需要封装的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String userName = authentication.getUserAuthentication().getName();
                // 数据库中查询用户信息
                QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
                wrapper.eq("username", userName);
                UserInfo userInfo = userMapper.selectOne(wrapper);
                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
                final Map<String, Object> additionalInformation = new HashMap<>();
                additionalInformation.put("userInfo", JSON.toJSONString(userInfo));
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken auth2AccessToken = super.enhance(accessToken, authentication);
                String key =  Constant.TOKEN_KEY + userName;
                redisTemplate.opsForList().leftPush(key , auth2AccessToken.getValue());
                return auth2AccessToken;
            }
        };
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey(SIGNING_KEY);
        return accessTokenConverter;
    }
}
