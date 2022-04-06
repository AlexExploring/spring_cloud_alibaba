package com.zhx.order.controller;

import com.zhx.order.feign.ProductFeignService;
import com.zhx.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private ProductFeignService productFeignService;

    @RequestMapping("/add")
    public String add() {
        String msg = stockFeignService.reduce();
        String msg1 = productFeignService.get(1);
        return "Hello World!  msg:"+msg+"   msg1:"+msg1;
    }
}
