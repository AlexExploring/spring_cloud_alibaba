package com.zhx.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhx.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/flow")
    @SentinelResource(value = "flow",blockHandler = "blockHandlerForFlow")
    public String flow() {
        return "正常访问";
    }

    public String blockHandlerForFlow(BlockException e) {
        return "QPS流控";
    }

    @RequestMapping("/flowThread")
    @SentinelResource(value = "flowThread",blockHandler = "blockHandlerForFlowThread")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "正常访问";
    }

    public String blockHandlerForFlowThread(BlockException e) {
        return "线程流控";
    }

    @GetMapping("/add")
    public String add() {
        return "生成订单";
    }

    @GetMapping("/get")
    public String get() {
        return "查询订单";
    }

    @Autowired
    private OrderService orderService;

    @GetMapping("/test1")
    public String test1() {
        return "test1:"+orderService.getUser();
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2:"+orderService.getUser();
    }

    @GetMapping("/err")
    public String err() {
        int i = 1/0;
        return "Hello";
    }

    @GetMapping("/get/{id}")
    @SentinelResource(value = "getById",blockHandler = "blockHandlerForGetById")
    public String getById(@PathVariable("id") Integer id) {
        return "正常访问";
    }

    public String blockHandlerForGetById(@PathVariable("id") Integer id, BlockException e) {
        return "热点异常处理";
    }
}
