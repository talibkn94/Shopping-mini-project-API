package com.shopping.Mini.Project.controller;

import com.shopping.Mini.Project.entity.Order;
import com.shopping.Mini.Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST,value = "/order")
    public Order placeOrder(@RequestBody Order order){
        System.out.println(order);
        try {
            return orderService.orderCreated(order);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }

}