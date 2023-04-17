package com.visonet.vamsidhar.ordermanagement.controller;

import com.visonet.vamsidhar.ordermanagement.model.dto.Order;
import com.visonet.vamsidhar.ordermanagement.service.OrderManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderManagementController {

    private OrderManagementService orderManagementService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderManagementService.createNewOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    public ResponseEntity<List<Order>> createOrder(@RequestBody List<Order> orders) {
        List<Order> newOrder = orderManagementService.createBatch(orders);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> listOrders() {
        List<Order> orders = orderManagementService.listAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
