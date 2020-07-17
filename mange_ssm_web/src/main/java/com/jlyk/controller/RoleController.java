package com.jlyk.controller;

import com.jlyk.domian.Permission;
import com.jlyk.domian.Role;
import com.jlyk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String saveRole(Role role){

        roleService.saveRole(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findUserByIdAllRole(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> permissionList = roleService.findOtherPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        System.out.println(permissionList);
        mv.setViewName("role-permission-add");
        return mv;

    }

    @RequestMapping("/addPermissionToRole.do")
    public  String addPermissionToRole(String roleId,String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);

        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

}
