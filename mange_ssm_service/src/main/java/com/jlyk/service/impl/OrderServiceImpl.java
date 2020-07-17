package com.jlyk.service.impl;

import com.github.pagehelper.PageHelper;
import com.jlyk.dao.OrdersDao;
import com.jlyk.domian.Orders;
import com.jlyk.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrdersDao ordersDao;

    public OrderServiceImpl(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return  ordersDao.findAll();

    }

    @Override
    public Orders finById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
