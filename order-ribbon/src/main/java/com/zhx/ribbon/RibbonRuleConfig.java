package com.zhx.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonRuleConfig {

    /**
     * 方法名一定要叫 iRule()
     */
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
