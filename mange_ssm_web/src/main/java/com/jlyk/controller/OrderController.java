package com.jlyk.controller;

import com.github.pagehelper.PageInfo;
import com.jlyk.domian.Orders;
import com.jlyk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    /*
    * 未分页代码
    * */
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll(){
//        List<Orders> ordersList= orderService.findAll();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("ordersList",ordersList);
//        modelAndView.setViewName("orders-list");
//        return modelAndView;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name = "size",defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Orders> orderList = orderService.findAll(page, size);
        PageInfo pageInfo =new PageInfo(orderList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId){
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.finById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
