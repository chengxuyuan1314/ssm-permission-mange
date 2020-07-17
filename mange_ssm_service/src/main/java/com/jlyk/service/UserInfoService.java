package com.jlyk.service;

import com.jlyk.domian.Role;
import com.jlyk.domian.UserInfo;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends UserDetailsService {
    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String id, String[] ids);
}
