package com.wpp.oauth2.iotdev.controller;

import com.wpp.oauth2.iotdev.common.User;
import com.wpp.oauth2.iotdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/list" )
public class OrderController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> userList() {
        return userService.userList();
    }

}
