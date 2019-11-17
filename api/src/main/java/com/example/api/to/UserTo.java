package com.example.api.to;

import com.example.api.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangpp
 */
@Data
@AllArgsConstructor
public class UserTo {
    private Integer pagenum;
    private Integer total;
    private List<UserInfo> users;
}
