package com.wpp.security.distributed.uaa.mapper;


import com.wpp.security.distributed.uaa.UAAServer;
import com.wpp.security.distributed.uaa.common.Permission;
import com.wpp.security.distributed.uaa.common.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UAAServer.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByUsername() {
        User wpp = userMapper.findByUsername("wpp");
        User admin = userMapper.findByUsername("admin");
        Assert.assertNotNull(wpp);
        Assert.assertNotNull(admin);
        Assert.assertTrue(wpp.getId().equals(2));
        Assert.assertTrue(admin.getId().equals(1));
        Assert.assertTrue(wpp.getUsername().equals("wpp"));
        Assert.assertTrue(admin.getUsername().equals("admin"));
    }

    @Test
    public void findPermissionByUsername() {
        List<Permission> admin = userMapper.findPermissionByUsername("admin");
        Assert.assertNotNull(admin);
        Assert.assertEquals(admin.size(), 4);

        List<Permission> wpp = userMapper.findPermissionByUsername("wpp");
        Assert.assertNotNull(wpp);
        Assert.assertEquals(wpp.size(), 2);

    }

    @Test
    public void updateUser() {
        User user = userMapper.findByUsername("admin");
        String pass = passwordEncoder.encode("admin");
        user.setPassword(pass);
        userMapper.updateUser(user);
        User wpp = userMapper.findByUsername("admin");
        String password = wpp.getPassword();
        boolean matches = passwordEncoder.matches("admin", password);
        Assert.assertTrue(matches);


        user = userMapper.findByUsername("wpp");
        pass = passwordEncoder.encode("wpp");
        user.setPassword(pass);
        userMapper.updateUser(user);


    }
}