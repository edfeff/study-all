package com.wpp.controller;

import com.wpp.model.AuthenticationRequest;
import com.wpp.model.UserDto;
import com.wpp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author wangpp
 */
@RestController
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping( value = "/login", produces = {"text/plain;charset=utf-8;"} )
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto authentication = authenticationService.authentication(authenticationRequest);
        session.setAttribute(UserDto.SESSION_USER_KEY, authentication);
        return authentication.getFullname() + " login success";
    }

    @GetMapping( value = "/user/{username}", produces = {"text/plain;charset=utf-8;"} )
    public String getuser(@PathVariable( value = "username", required = false ) String username, HttpSession session) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        UserDto userDto = (UserDto) session.getAttribute(UserDto.SESSION_USER_KEY);
        if (userDto == null ||
                !userDto.getUsername().equals(username)) {
            return "用户未登录";
        }
        return "用户：" + userDto.getFullname() + ":" + userDto.getMobile();
    }

}
