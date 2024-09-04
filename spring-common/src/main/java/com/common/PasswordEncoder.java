package com.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @data 2023/8/17 15:19
 */
@Component
public class PasswordEncoder  extends BCryptPasswordEncoder {
}
