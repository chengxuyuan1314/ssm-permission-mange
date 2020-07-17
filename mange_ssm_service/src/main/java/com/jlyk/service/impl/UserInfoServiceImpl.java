package com.jlyk.service.impl;

import com.jlyk.dao.UserInfoDao;
import com.jlyk.domian.Role;
import com.jlyk.domian.UserInfo;
import com.jlyk.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service("userInfoService")
public class UserInfoServiceImpl  implements UserInfoService  {
    //构造方法注入
    private final UserInfoDao userInfoDao;
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //查询所有用户
    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    //保存用户
    @Override
    public void saveUser(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.saveUser(userInfo);
    }
    //根据id 查询用户详情
    @Override
    public UserInfo findById(String id) {
      return  userInfoDao.findById(id);

    }
    //查询用户其他权限
    @Override
    public List<Role> findOtherRoles(String id) {
        return userInfoDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {


        for (String roleId : ids) {

            userInfoDao.addRoleToUser(userId,roleId);
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.findByUserName(username);
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
