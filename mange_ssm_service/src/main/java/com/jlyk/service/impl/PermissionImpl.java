package com.jlyk.service.impl;

import com.jlyk.dao.PermissionDao;
import com.jlyk.domian.Permission;
import com.jlyk.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return  permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.save(permission);
    }
}
