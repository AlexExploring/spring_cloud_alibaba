package com.zhx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class AlibabaSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSeataOrderApplication.class);
    }
}
