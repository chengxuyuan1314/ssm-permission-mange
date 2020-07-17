package com.jlyk.service;

import com.jlyk.domian.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAll(int page,int size);

    Orders finById(String ordersId);
}
