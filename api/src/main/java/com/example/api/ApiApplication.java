package com.example.api;

import com.example.api.pojo.User;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

/**
 * @author thisisit
 */
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }



    public void setUser(User user, Errors errors) {

    }
}
