package com.wpp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author wangpp
 */
@Component
public class MyPasswordEncoder extends BCryptPasswordEncoder {
}
