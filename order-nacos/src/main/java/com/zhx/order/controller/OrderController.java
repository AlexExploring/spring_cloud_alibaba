package com.zhx.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add() {
        String msg = restTemplate.getForObject("http://stock-nacos/stock/reduce", String.class);
        return "Hello World!  "+msg;
    }

    //下面的接口来源于order-sentinel
    @RequestMapping("/flow")
    public String flow() {
        return "正常访问";
    }

    @RequestMapping("/flowThread")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "正常访问";
    }

    @GetMapping("/get")
    public String get() {
        return "查询订单";
    }


    @GetMapping("/err")
    public String err() {
        int i = 1/0;
        return "Hello";
    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable("id") Integer id) {
        return "正常访问";
    }
}
