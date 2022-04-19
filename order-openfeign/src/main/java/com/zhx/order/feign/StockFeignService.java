package com.zhx.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//局部配置  @FeignClient(name = "stock-service",path = "/stock",configuration = FeignConfig.class)
@FeignClient(name = "stock-nacos",path = "/stock")
public interface StockFeignService {

    @GetMapping("/reduce2")
    public String reduce2();
}
