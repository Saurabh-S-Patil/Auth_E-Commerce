package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunbeaminfo.entities.Orders;

@Service
public interface OrderService {
Orders createOrder(Orders order);
List<Orders> getAllOrders(Iterable<Long> id) ;
Orders getOrderById(Long id);
boolean deleteOrder(Long id);
boolean updateOrder(Long id, Orders order);
}