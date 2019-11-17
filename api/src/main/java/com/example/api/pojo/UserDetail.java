package com.example.api.pojo;

import lombok.Data;

/**
 * @author wangpp
 */
@Data
public class UserDetail {
    private String username;
    private String password;
    private String email;
    private Integer id;
    private Integer rid;
    private String mobile;
    private String token;
}
