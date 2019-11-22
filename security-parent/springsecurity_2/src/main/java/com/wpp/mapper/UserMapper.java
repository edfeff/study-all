package com.wpp.mapper;

import com.wpp.domain.Permission;
import com.wpp.domain.User;

import java.util.List;

/**
 * @author wpp
 */
public interface UserMapper {
    User findByUsername(String username);

    List<Permission> findPermissionByUsername(String username);

}
