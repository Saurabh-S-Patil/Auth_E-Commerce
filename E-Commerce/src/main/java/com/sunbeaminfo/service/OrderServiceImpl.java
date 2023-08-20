package com.sunbeaminfo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunbeaminfo.dao.OrderRepository;
import com.sunbeaminfo.entities.Orders;


@Service
public class OrderServiceImpl implements OrderService{

private final OrderRepository orderRepository;

public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
}

@Override
public Orders createOrder(Orders order) {
return orderRepository.save(order);
}

@Override
public List<Orders> getAllOrders(Iterable<Long> id) {
List<Orders> list = orderRepository.findAllById(id);
return list;
}

@Override
public Orders getOrderById(Long id) {

return orderRepository.getById(id);
}

@Override
public boolean deleteOrder(Long id) {
orderRepository.deleteById(id);
return true;
}

@Override
public boolean updateOrder(Long id, Orders order) {
      Optional<Orders> temp=orderRepository.findById(id);
      Orders updatedOrder = temp.get();
      updatedOrder.setOrderDate(order.getOrderDate());
      updatedOrder.setAddress(order.getAddress());
      updatedOrder.setUserQueries(order.getUserQueries());
      updatedOrder.setPaymentMethodId(order.getPaymentMethodId());
return true;
}

}
