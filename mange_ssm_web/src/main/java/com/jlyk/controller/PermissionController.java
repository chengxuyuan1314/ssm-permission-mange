package com.jlyk.controller;

import com.jlyk.domian.Permission;
import com.jlyk.service.PermissionService;
import com.jlyk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionSrvice;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionsList = permissionSrvice.findAll();
        mv.addObject("permissionList",permissionsList);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String saveRole(Permission permission){

        permissionSrvice.savePermission(permission);
        return "redirect:findAll.do";
    }
}
