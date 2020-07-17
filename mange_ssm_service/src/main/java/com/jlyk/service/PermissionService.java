package com.jlyk.service;

import com.jlyk.domian.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void savePermission(Permission permission);
}
