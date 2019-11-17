package com.example.api.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangpp
 */
@Data
public class Menu {
    private Integer id;
    private String authName;
    private String path;
    private List<Menu> children;

    public Menu(Integer id, String authName, String path) {
        this.id = id;
        this.authName = authName;
        this.path = path;
        this.children = new ArrayList<>();
    }

    public void addChildMenu(Menu menu) {
        this.children.add(menu);
    }

    public Menu() {
    }
}
