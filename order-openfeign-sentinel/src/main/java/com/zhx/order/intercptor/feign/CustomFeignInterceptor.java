package com.zhx.order.intercptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("feign拦截器");
    }
}
