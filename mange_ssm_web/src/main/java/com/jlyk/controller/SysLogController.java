package com.jlyk.controller;

import com.github.pagehelper.PageInfo;
import com.jlyk.domian.SysLog;
import com.jlyk.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "size",defaultValue = "4") Integer size){

        ModelAndView mv = new ModelAndView();

        List<SysLog> sysLogs = sysLogService.findAllSysLog(page,size);
        PageInfo pageInfo =new PageInfo(sysLogs);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
