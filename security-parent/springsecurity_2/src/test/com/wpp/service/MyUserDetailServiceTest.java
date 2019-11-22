package com.wpp.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyUserDetailServiceTest {

    @Autowired
    MyUserDetailService service;

    @Test
    public void loadUserByUsername() {
        UserDetails wpp = service.loadUserByUsername("wpp");
        assertNotNull(wpp);
        assertEquals(wpp.getAuthorities().size(), 2);

        UserDetails admin = service.loadUserByUsername("admin");
        assertNotNull(admin);
        assertEquals(admin.getAuthorities().size(), 4);

    }
}
