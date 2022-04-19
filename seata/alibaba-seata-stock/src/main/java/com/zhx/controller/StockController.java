package com.zhx.controller;

import com.zhx.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/reduce")
    public String reduce(@RequestParam("productId")  Long productId) {
        stockService.reduce(productId);
        return "扣减库存";
    }
}
