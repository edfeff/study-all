package com.example.api.rest;

import com.example.api.components.UserService;
import com.example.api.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/api/private/v1" )
public class ApiService {

    @Autowired
    UserService userService;

    @PostMapping( "/login" )
    public ResponseData login(@RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseData.fail(errors.getAllErrors());
        } else if (userService.checkUser(user.getUsername(), user.getPassword())) {

            return ResponseData.success(userService.getUser());
        } else {
            return ResponseData.fail("用户名或密码不正确");
        }
    }

    @PostMapping( "/menus" )
    public ResponseData getMenus(@RequestHeader( value = "Authorization" ) String tokenHeader) {
        User user = getUserFromToken(tokenHeader);
        if (user == null) {
            return ResponseData.fail("token 无效");
        }
        List<Menu> menus = userService.getMenu(user);
        return ResponseData.success(menus);
    }

    public User getUserFromToken(String token) {
        String[] s = token.split(" ");
        if (s.length != 2) {
//            throw new RuntimeException("token 格式错误:" + token);
            return null;
        } else {
            String[] userArr = s[1].split(":");
            if (userArr.length != 2) {
//                throw new RuntimeException("token 格式错误:" + token);
                return null;
            }
            return new User(userArr[0], userArr[1]);
        }
    }

    @PostMapping( "/checkToken" )
    public ResponseData checkToken(@RequestParam( value = "token", required = false ) String token,
                                   @RequestHeader( value = "Authorization", required = false ) String tokenHeader) {
        if (
                Token.token.equalsIgnoreCase(token)
                        || Token.token.equalsIgnoreCase(tokenHeader)
        ) {
            return ResponseData.success(userService.getUser());
        } else {
            return ResponseData.fail("token无效");
        }
    }

//    @GetMapping( "/users" )
//    public List<User> users() {
//        List<User> users = Arrays.asList(new User("admin", "admin"));
//        return users;
//    }

    @GetMapping( "/users" )
    public ResponseData userDetails(QueryInfo queryInfo) {
        return ResponseData.success(userService.getAllUsers(queryInfo));
    }

    @PutMapping( "/users/{uid}/state/{type}" )
    public ResponseData updateUserState(
            @PathVariable String uid,
            @PathVariable Boolean type
    ) {
        System.out.println(uid + " : " + type);
        return ResponseData.success();
    }


    @PostMapping( "/addUser" )
    public ResponseData addUser(@RequestBody UserDetail userDetail) {
        return ResponseData.success();
    }

    @DeleteMapping( "/deleteUser/{uid}" )
    public ResponseData addUser(@PathVariable Integer uid) {
        return ResponseData.success();
    }

    @GetMapping( "/rights/{type}" )
    public ResponseData addUser(@PathVariable String type) {
        return ResponseData.success(userService.getAllRights(type));
    }

    @GetMapping( "/roles" )
    public ResponseData getRoleList() {
        return ResponseData.success(userService.getRoleList());
    }

    @DeleteMapping( "/roles/{roleId}/rights/{rightId}" )
    public ResponseData getRoleList(@PathVariable Integer roleId,
                                    @PathVariable Integer rightId) {
        return ResponseData.success(userService.getRoleList());
    }

    public void setUser(User user, Errors errors) {
        System.out.println(errors.getAllErrors());
        return;
    }
}
