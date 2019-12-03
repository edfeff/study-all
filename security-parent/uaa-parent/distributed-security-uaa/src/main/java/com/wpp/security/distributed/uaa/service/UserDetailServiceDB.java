package com.wpp.security.distributed.uaa.service;

import com.wpp.security.distributed.uaa.common.Permission;
import com.wpp.security.distributed.uaa.common.User;
import com.wpp.security.distributed.uaa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class UserDetailServiceDB implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(s)) {
            throw new UsernameNotFoundException(s + " must not be empty");
        }
        User user = userMapper.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s + " not exist");
        }
        List<Permission> permission = userMapper.findPermissionByUsername(s);
        if (permission != null) {
            List<GrantedAuthority> grantedAuthorities = permission.stream()
                    .map(Permission::getPermTag).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            user.setAuthorities(grantedAuthorities);
        }
        return user;
    }
}
