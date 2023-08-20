package com.sunbeaminfo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeaminfo.DTO.OrderDTO;
import com.sunbeaminfo.entities.Orders;
import com.sunbeaminfo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService customerService;  

    public OrderController(OrderService customerService) {
        this.customerService = customerService;
    }
   
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Orders customer) {
        if (customer.getUser()!= null) {
            Orders createdOrder= customerService.createOrder(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created with ID: " + createdOrder.getId());
        } else {
        Orders createdOrder= customerService.createOrder(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created with ID: " + createdOrder.getId());
        }
    }
   
    @GetMapping("/get")
    public ResponseEntity<List<OrderDTO>> getCustomerList(@RequestParam("uuid") Iterable<Long> id) {
        List<Orders> orders= customerService.getAllOrders(id);
        List<OrderDTO> orderDTOs = new ArrayList();
for (Orders order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrder_date(order.getOrderDate());
            orderDTO.setOrder_status(order.isOrderStatus());
            orderDTO.setAddress_id(order.getAddress().getId());
            orderDTO.setUser_id(order.getUser().getId());            
         
            orderDTOs.add(orderDTO);
        }
        return ResponseEntity.ok(orderDTOs);
    }
   
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam("uuid") Long id) {
            boolean deleted = customerService.deleteOrder(id);
            if (deleted) {
                return ResponseEntity.ok("Successfully deleted customer with ID: " + id);
            } else {
                return ResponseEntity.badRequest().body("Order not found with ID: " + id);
            }
    }
   
    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestParam("uuid") Long id, @RequestBody Orders order) {
            order.setId(id);
            boolean updated = customerService.updateOrder(id, order);
            if (updated) {
                return ResponseEntity.ok("Successfully updated customer with ID: " + id);
            } else {
                return ResponseEntity.badRequest().body("Customer not found with ID: " + id);
            }
    }
   
   
}
