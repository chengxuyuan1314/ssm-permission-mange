package com.jlyk.service;

import com.jlyk.domian.Permission;
import com.jlyk.domian.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void saveRole(Role role);

    Role findById(String id);



    List<Permission> findOtherPermission(String id);


    void addPermissionToRole(String userId, String[] ids);
}
