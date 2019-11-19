package com.example.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangpp
 */
@Data
@AllArgsConstructor
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<Menu> children;
}
