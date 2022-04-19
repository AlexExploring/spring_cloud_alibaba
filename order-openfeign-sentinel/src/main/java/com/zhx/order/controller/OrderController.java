package com.zhx.order.controller;

import com.zhx.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockFeignService stockFeignService;

    @RequestMapping("/add")
    public String add() {
        String msg = stockFeignService.reduce2();
        return "Hello World!  msg:"+msg;
    }
}
