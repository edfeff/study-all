package com.example.api.components;

import com.example.api.pojo.Menu;
import com.example.api.pojo.Token;
import com.example.api.pojo.User;
import com.example.api.pojo.UserDetail;
import com.example.api.rest.QueryInfo;
import com.example.api.to.UserInfo;
import com.example.api.to.UserTo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wangpp
 */
@Service
public class UserService {
    public UserDetail getUser() {
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername("admin");
        userDetail.setToken(Token.token);
        userDetail.setEmail("edfeff@163.com");
        userDetail.setId(1);
        userDetail.setRid(1);
        userDetail.setMobile("110");
        return userDetail;
    }

    public boolean checkUser(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    public List<Menu> getMenu(User user) {
        List<Menu> menus = new ArrayList<>();
        Menu usersMenu = new Menu(125, "用户管理", "users");
        Menu rightsMenu = new Menu(126, "权限管理", "rights");
        Menu goodsMenu = new Menu(127, "商品管理", "goods");
        Menu ordersMenu = new Menu(128, "订单管理", "orders");
        Menu reportsMenu = new Menu(129, "数据统计", "reports");

        Menu usersMenu_1 = new Menu(1251, "用户列表", "users");
//        Menu usersMenu_2 = new Menu(1252, "用户管理-B", "users/b");
//        Menu usersMenu_3 = new Menu(1253, "用户管理-C", "users/c");
        Menu rightsMenu_1 = new Menu(1261, "权限管理-A", "rights/a");
        Menu goodsMenu_1 = new Menu(1271, "商品管理-A", "goods/a");
        Menu ordersMenu_1 = new Menu(1281, "订单管理-A", "orders/a");
        Menu reportsMenu_1 = new Menu(1291, "数据统计-A", "reports/a");

        usersMenu.addChildMenu(usersMenu_1);
//        usersMenu.addChildMenu(usersMenu_2);
//        usersMenu.addChildMenu(usersMenu_3);
        rightsMenu.addChildMenu(rightsMenu_1);
        goodsMenu.addChildMenu(goodsMenu_1);
        ordersMenu.addChildMenu(ordersMenu_1);
        reportsMenu.addChildMenu(reportsMenu_1);

        menus.add(usersMenu);
        menus.add(rightsMenu);
        menus.add(goodsMenu);
        menus.add(ordersMenu);
        menus.add(reportsMenu);
        return menus;
    }


    public UserTo getAllUsers(QueryInfo queryInfo) {
        UserInfo info1 = new UserInfo(200, "admin", "admin", System.currentTimeMillis(), "110", true, "admin@qq.com");
        UserInfo info2 = new UserInfo(201, "wpp", "common", System.currentTimeMillis(), "119", true, "wpp@qq.com");
        UserInfo info3 = new UserInfo(202, "test", "test", System.currentTimeMillis(), "120", true, "test@qq.com");
        Integer pagenum = queryInfo.getPagenum();
        Integer pagesize = queryInfo.getPagesize();
        List<UserInfo> list = new ArrayList<>();
        for (int i = pagenum; i < pagenum + pagesize; i++) {
            UserInfo info = new UserInfo(200 + i + pagenum * pagesize, "user" + i, "role" + i,
                    System.currentTimeMillis(),
                    "13866886688", true, "user" + i + "@qq.com");
            if (!StringUtils.isEmpty(queryInfo.getQuery())) {
                info.setUsername("user-" + queryInfo.getQuery());
            }
            list.add(info);
        }

        UserTo userTo = new UserTo(pagenum, 100, list);
        return userTo;
    }
}

