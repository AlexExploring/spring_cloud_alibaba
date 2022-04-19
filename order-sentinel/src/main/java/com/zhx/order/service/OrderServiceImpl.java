package com.zhx.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Override
    @SentinelResource(value = "getUser",blockHandler = "blockHandlerForGetUser")
    public String getUser() {
        return "查询用户";
    }

    public String blockHandlerForGetUser(BlockException e) {
        return "查询用户流控";
    }
}
