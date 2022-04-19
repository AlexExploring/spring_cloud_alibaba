package com.zhx.service.impl;

import com.zhx.mapper.StockMapper;
import com.zhx.service.StockService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @GlobalTransactional
    @Override
    public void reduce(Long productId) {
        System.out.println("更新商品:"+productId);
        stockMapper.reduce(productId);
    }
}
