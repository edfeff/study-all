package com.example.api.to;

import lombok.Data;

/**
 * @author wangpp
 */
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String roleName;
    private long createTime;
    private String mobile;
    private boolean msgState;
    private String email;

    public UserInfo(Integer id, String username, String roleName, long createTime, String mobile, boolean msgState,String email) {
        this.id = id;
        this.username = username;
        this.roleName = roleName;
        this.createTime = createTime;
        this.mobile = mobile;
        this.msgState = msgState;
        this.email = email;
    }
}
