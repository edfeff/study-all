package com.wpp.service;

import com.wpp.model.AuthenticationRequest;
import com.wpp.model.UserDto;

/**
 * @author wangpp
 */
public interface AuthenticationService {
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
