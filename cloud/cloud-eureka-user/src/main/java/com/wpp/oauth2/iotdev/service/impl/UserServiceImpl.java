package com.wpp.oauth2.iotdev.service.impl;

import com.wpp.oauth2.iotdev.common.User;
import com.wpp.oauth2.iotdev.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> userList() {
        return Arrays.asList(new User("wpp", "xuzhou"));
    }
}
