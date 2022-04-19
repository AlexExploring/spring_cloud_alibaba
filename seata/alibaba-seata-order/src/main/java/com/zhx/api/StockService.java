package com.zhx.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "alibaba-seata-stock",path = "/stock")
public interface StockService {

    @RequestMapping("/reduce")
    public String reduce(@RequestParam("productId")  Long productId);
}
