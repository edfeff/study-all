package com.wpp.security.distributed.uaa.mapper;

import com.wpp.security.distributed.uaa.common.Permission;
import com.wpp.security.distributed.uaa.common.User;

import java.util.List;

/**
 * @author wpp
 */
public interface UserMapper {
    User findByUsername(String username);

    List<Permission> findPermissionByUsername(String username);

    int updateUser(User user);

}
