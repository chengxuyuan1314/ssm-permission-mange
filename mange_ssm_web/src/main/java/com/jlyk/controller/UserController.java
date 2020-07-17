package com.jlyk.controller;

import com.jlyk.domian.Role;
import com.jlyk.domian.UserInfo;
import com.jlyk.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userInfoService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo){

        userInfoService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id") String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userInfoService.findById(id);
        System.out.println(userInfo);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;

    }



    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAllRole(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userInfoService.findById(id);
        List<Role> roleList = userInfoService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;

    }


    @RequestMapping("/addRoleToUser.do")
    public  String andRoleToUser(String userId,String[] ids){

        userInfoService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

}
