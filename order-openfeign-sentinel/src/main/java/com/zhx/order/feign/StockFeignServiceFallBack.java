package com.zhx.order.feign;

import org.springframework.stereotype.Component;

@Component
public class StockFeignServiceFallBack implements StockFeignService{

    @Override
    public String reduce2() {
        return "降级啦";
    }
}
