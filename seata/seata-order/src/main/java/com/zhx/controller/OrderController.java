package com.zhx.controller;

import com.zhx.pojo.Order;
import com.zhx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/add")
    public String add() {
        Order order = new Order();
        order.setProductId(9l);
        order.setStatus("未支付");
        order.setTotalAmount(10);
        orderService.create(order);
        return "下单成功";
    }
}
