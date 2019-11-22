package com.wpp.service;

import com.wpp.domain.Permission;
import com.wpp.domain.User;
import com.wpp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        List<Permission> permission = userMapper.findPermissionByUsername(s);
        if (permission != null) {
            List<GrantedAuthority> grantedAuthorities = permission.stream()
                    .map(Permission::getPermTag).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            user.setAuthorities(grantedAuthorities);
        }
        return user;
    }
}
