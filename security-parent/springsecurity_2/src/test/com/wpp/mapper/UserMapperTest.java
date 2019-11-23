package com.wpp.mapper;

import com.wpp.domain.Permission;
import com.wpp.domain.User;
import com.wpp.security.MyPasswordEncoder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:applicationContext.xml" )
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

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
        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String pass = passwordEncoder.encode("admin");
        user.setPassword(pass);
        userMapper.updateUser(user);
        User wpp = userMapper.findByUsername("admin");
        String password = wpp.getPassword();
        boolean matches = passwordEncoder.matches("admin", password);
        Assert.assertTrue(matches);
    }
}
