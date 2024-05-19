package com.springapps.shop.controllers;

import com.springapps.shop.entities.Order;
import com.springapps.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Order> addOrderByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.addOrderToUser(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> viewAllOrdersByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.viewOrders(userId));
    }


}
