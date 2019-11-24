package com.wpp.service;

import com.wpp.model.AuthenticationRequest;
import com.wpp.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpp
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * 校验用户的身份信息
     *
     * @param authenticationRequest
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("authenticationRequest or username or password is empty");
        }
        UserDto userDto = getUser(authenticationRequest.getUsername());

        if (userDto == null) {
            throw new RuntimeException("user is not exists");
        }

        if (!userDto.getPassword().equals(authenticationRequest.getPassword())) {
            throw new RuntimeException("passsword is not correct");
        }
        return userDto;
    }

    private UserDto getUser(String username) {
        return userMap().get(username);
    }

    public Map<String, UserDto> userMap() {
        Map<String, UserDto> map = new HashMap<>();
        map.put("admin", new UserDto("1010", "admin", "admin", "administrator", "110"));
        map.put("wpp", new UserDto("1011", "wpp", "wpp", "pengpeng.wang", "120"));
        return map;
    }
}
