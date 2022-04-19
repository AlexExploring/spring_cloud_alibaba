package com.zhx.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    String port;

    @GetMapping("/reduce")
    public String reduce() {
        return "扣减库存:"+port;
    }

    @GetMapping("/reduce2")
    public String reduce2() {
        int a = 1/0;
        return "扣减库存2:"+port;
    }
}
