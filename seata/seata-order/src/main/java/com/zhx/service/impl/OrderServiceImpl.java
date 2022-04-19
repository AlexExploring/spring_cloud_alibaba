package com.zhx.service.impl;

import com.zhx.mapper.OrderMapper;
import com.zhx.pojo.Order;
import com.zhx.service.OrderService;
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
    private RestTemplate restTemplate;

    @Transactional
    @Override
    public Order create(Order order) {
        orderMapper.insert(order);

        MultiValueMap<String,Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("productId",order.getProductId());

        String msg = restTemplate.postForObject("http://localhost:8071/stock/reduce",paramMap,String.class);

        int a = 1/0;

        return order;
    }
}
