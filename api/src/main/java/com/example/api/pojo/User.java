package com.example.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * @author wangpp
 */
@Data
@AllArgsConstructor
@Validated
public class User {
    @NotEmpty( message = "username is empty" )
    private String username;
    @NotEmpty( message = "password is empty" )
    private String password;
}
