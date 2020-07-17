package com.jlyk.service.impl;

import com.jlyk.dao.RoleDao;
import com.jlyk.domian.Permission;
import com.jlyk.domian.Role;
import com.jlyk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private  RoleDao  roleDao;
    @Override
    public List<Role> findAll() {
       return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
       return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String id) {
      return   roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
