package com.zhx.service.impl;

import com.zhx.api.StockService;
import com.zhx.mapper.OrderMapper;
import com.zhx.pojo.Order;
import com.zhx.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StockService stockService;

    @GlobalTransactional
    @Override
    public Order create(Order order) {
        orderMapper.insert(order);

        stockService.reduce(order.getProductId());

        //产生一个异常
        int a = 1/0;

        return order;
    }
}
