package com.example.api.components;

import com.example.api.pojo.*;
import com.example.api.rest.QueryInfo;
import com.example.api.to.UserInfo;
import com.example.api.to.UserTo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

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
        Menu rightsMenu_1 = new Menu(1261, "角色管理", "roles");
        Menu rightsMenu_2 = new Menu(1262, "权限配置", "rights");
        Menu goodsMenu_1 = new Menu(1271, "商品管理-A", "goods/a");
        Menu ordersMenu_1 = new Menu(1281, "订单管理-A", "orders/a");
        Menu reportsMenu_1 = new Menu(1291, "数据统计-A", "reports/a");

        usersMenu.addChildMenu(usersMenu_1);
        rightsMenu.addChildMenu(rightsMenu_1);
        rightsMenu.addChildMenu(rightsMenu_2);
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

    public List<Right> getAllRights(String type) {

        List<Right> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(new Right(i + 1, "权限-" + i, i % 4, "/right/auth" + i, "pid-" + i));
        }
        return list;
    }

    public List<Role> getRoleList(Integer roleId, Integer rightId) {
        List<Role> roles = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        Menu usersMenu = new Menu(125, "用户管理", "users");
        Menu usersMenu_1 = new Menu(1251, "用户列表", "users");
        Menu usersMenu_1_1 = new Menu(12511, "用户添加" + rightId, "users");
        Menu usersMenu_1_2 = new Menu(12512, "用户删除" + rightId, "users");
        Menu usersMenu_1_3 = new Menu(12513, "用户修改" + rightId, "users");
        Menu usersMenu_1_4 = new Menu(12514, "用户查询" + rightId, "users");
        usersMenu_1.addChildMenu(usersMenu_1_1);
        usersMenu_1.addChildMenu(usersMenu_1_2);
        usersMenu_1.addChildMenu(usersMenu_1_3);
        usersMenu_1.addChildMenu(usersMenu_1_4);
        usersMenu.addChildMenu(usersMenu_1);
        menus.add(usersMenu);
        Role role = new Role(roleId, "角色: " + rightId, "描述:" + roleId, menus);
        roles.add(role);
        return roles;
    }

    public List<Role> getRoleList() {
        List<Role> roles = new ArrayList<>();

        List<Menu> menus = new ArrayList<>();
        Menu usersMenu = new Menu(125, "用户管理", "users");
        Menu rightsMenu = new Menu(126, "权限管理", "rights");
        Menu goodsMenu = new Menu(127, "商品管理", "goods");
        Menu ordersMenu = new Menu(128, "订单管理", "orders");
        Menu reportsMenu = new Menu(129, "数据统计", "reports");

        Menu usersMenu_1 = new Menu(1251, "用户列表", "users");
        Menu rightsMenu_1 = new Menu(1261, "角色管理", "roles");
        Menu rightsMenu_2 = new Menu(1262, "权限配置", "rights");
        Menu goodsMenu_1 = new Menu(1271, "商品管理-A", "goods/a");
        Menu ordersMenu_1 = new Menu(1281, "订单管理-A", "orders/a");
        Menu reportsMenu_1 = new Menu(1291, "数据统计-A", "reports/a");

        Menu usersMenu_1_1 = new Menu(12511, "用户添加", "users");
        Menu usersMenu_1_2 = new Menu(12512, "用户删除", "users");
        Menu usersMenu_1_3 = new Menu(12513, "用户修改", "users");
        Menu usersMenu_1_4 = new Menu(12514, "用户查询", "users");
        Menu rightsMenu_1_1 = new Menu(12611, "角色添加", "roles");
        Menu rightsMenu_1_2 = new Menu(12612, "角色删除", "roles");
        Menu rightsMenu_1_3 = new Menu(12613, "角色修改", "roles");
        Menu rightsMenu_1_4 = new Menu(12614, "角色查询", "roles");
        Menu rightsMenu_2_1 = new Menu(12621, "权限添加", "rights");
        Menu rightsMenu_2_2 = new Menu(12622, "权限删除", "rights");
        Menu rightsMenu_2_3 = new Menu(12623, "权限修改", "rights");
        Menu rightsMenu_2_4 = new Menu(12614, "权限查询", "rights");
        Menu goodsMenu_1_1 = new Menu(12711, "商品添加", "goods/a");
        Menu goodsMenu_1_2 = new Menu(12712, "商品删除", "goods/a");
        Menu goodsMenu_1_3 = new Menu(12713, "商品修改", "goods/a");
        Menu goodsMenu_1_4 = new Menu(12714, "商品查询", "goods/a");
        Menu ordersMenu_1_1 = new Menu(12811, "订单添加", "orders/a");
        Menu ordersMenu_1_2 = new Menu(12812, "订单删除", "orders/a");
        Menu ordersMenu_1_3 = new Menu(12813, "订单修改", "orders/a");
        Menu ordersMenu_1_4 = new Menu(12814, "订单查询", "orders/a");
        Menu reportsMenu_1_1 = new Menu(12911, "数据添加", "reports/a");
        Menu reportsMenu_1_2 = new Menu(12912, "数据删除", "reports/a");
        Menu reportsMenu_1_3 = new Menu(12913, "数据修改", "reports/a");
        Menu reportsMenu_1_4 = new Menu(12914, "数据查询", "reports/a");

        usersMenu_1.addChildMenu(usersMenu_1_1);
        usersMenu_1.addChildMenu(usersMenu_1_2);
        usersMenu_1.addChildMenu(usersMenu_1_3);
        usersMenu_1.addChildMenu(usersMenu_1_4);
        rightsMenu_1.addChildMenu(rightsMenu_1_1);
        rightsMenu_1.addChildMenu(rightsMenu_1_2);
        rightsMenu_1.addChildMenu(rightsMenu_1_3);
        rightsMenu_1.addChildMenu(rightsMenu_1_4);
        goodsMenu_1.addChildMenu(goodsMenu_1_1);
        goodsMenu_1.addChildMenu(goodsMenu_1_2);
        goodsMenu_1.addChildMenu(goodsMenu_1_3);
        goodsMenu_1.addChildMenu(goodsMenu_1_4);
        ordersMenu_1.addChildMenu(ordersMenu_1_1);
        ordersMenu_1.addChildMenu(ordersMenu_1_2);
        ordersMenu_1.addChildMenu(ordersMenu_1_3);
        ordersMenu_1.addChildMenu(ordersMenu_1_4);
        reportsMenu_1.addChildMenu(reportsMenu_1_1);
        reportsMenu_1.addChildMenu(reportsMenu_1_2);
        reportsMenu_1.addChildMenu(reportsMenu_1_3);
        reportsMenu_1.addChildMenu(reportsMenu_1_4);
        rightsMenu_2.addChildMenu(rightsMenu_2_1);
        rightsMenu_2.addChildMenu(rightsMenu_2_2);
        rightsMenu_2.addChildMenu(rightsMenu_2_3);
        rightsMenu_2.addChildMenu(rightsMenu_2_4);


        usersMenu.addChildMenu(usersMenu_1);
        rightsMenu.addChildMenu(rightsMenu_1);
        rightsMenu.addChildMenu(rightsMenu_2);
        goodsMenu.addChildMenu(goodsMenu_1);
        ordersMenu.addChildMenu(ordersMenu_1);
        reportsMenu.addChildMenu(reportsMenu_1);

        menus.add(usersMenu);
        menus.add(rightsMenu);
        menus.add(goodsMenu);
        menus.add(ordersMenu);
        menus.add(reportsMenu);

        for (int i = 1; i <= 6; i++) {
            Role role = new Role(i, "角色: " + i, "描述:" + i, menus);
            roles.add(role);
        }
        return roles;
    }
}

