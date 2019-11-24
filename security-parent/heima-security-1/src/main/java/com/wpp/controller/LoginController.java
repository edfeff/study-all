package com.wpp.controller;

import com.wpp.model.AuthenticationRequest;
import com.wpp.model.UserDto;
import com.wpp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping( value = "/login", produces = {"text/plain;charset=utf-8;"} )
    public String login(AuthenticationRequest authenticationRequest) {
        String result;
        UserDto authentication = authenticationService.authentication(authenticationRequest);
        return authentication.getFullname() + " login success";
    }
}
