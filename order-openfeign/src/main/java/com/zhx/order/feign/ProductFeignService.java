package com.zhx.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "product-service",path = "/product")
public interface ProductFeignService {

    @RequestMapping("/{id}")
    String get(@PathVariable("id") Integer id);
}
