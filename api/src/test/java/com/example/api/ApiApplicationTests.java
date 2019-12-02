package com.example.api;

import com.example.api.rest.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ApiService apiService;

    @Test
    void setUser() {

    }
}
