package com.wpp.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author wangpp
 */
public class ImageCodeException extends AuthenticationException {
    public ImageCodeException(String msg) {
        super(msg);
    }

    public ImageCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
