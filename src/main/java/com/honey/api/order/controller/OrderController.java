package com.honey.api.order.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.honey.api.order.common.Payment;
import com.honey.api.order.common.TransactionRequest;
import com.honey.api.order.common.TransactionResponse;
import com.honey.api.order.entity.Order;
import com.honey.api.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/getOrders")
    public List<Order> getAllOrders(){
        return service.getOrders();
    }
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {

    return service.saveOrder(request);
    }

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable("orderId") int orderId){
        return service.getOrderById(orderId);
    }

}
