package com.pub.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.PasswordEncoder;
import com.common.mapper.*;
import com.common.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lx
 * @data 2023/8/18 9:18
 */
@Slf4j
@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 数据库中查询用户信息
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserInfo user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }

        return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()));
    }
}
