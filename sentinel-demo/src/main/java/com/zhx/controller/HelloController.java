package com.zhx.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class HelloController {

    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user" ;
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    @GetMapping(value = "/hello")
    public String hello() {
        Entry entry = null;
        try {
            SphU.entry(RESOURCE_NAME);
            String str = "Hello World!";
            log.info("--------------"+str+"--------------");
            return str;
        } catch (BlockException e) {
            log.info("blocked");
            return "被流控了";
        } catch (Exception e) {
            Tracer.traceEntry(e,entry);
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    @PostConstruct
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        //流控
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        //设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源阈值
        rule.setCount(1);
        rules.add(rule);

        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }
}
