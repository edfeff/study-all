package com.wpp.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpp
 */
public class MyUserDetailService implements UserDetailsService, InitializingBean {
    private Map<String, UserDetails> internalUser;

    @Override
    public void afterPropertiesSet() throws Exception {
        internalUser = new HashMap<>();
        internalUser.put("wpp", new User("wpp", "wpp", AuthorityUtils.createAuthorityList("ROLE_USER")));
        internalUser.put("admin", new User("admin", "admin", AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return internalUser.get(username);
    }


}
