package com.zhx.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.zhx.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class HelloController {

    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "block" ;
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

        //block的流控规则
        FlowRule rule1 = new FlowRule();
        rule1.setResource(USER_RESOURCE_NAME);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setCount(1);
        rules.add(rule1);

        FlowRuleManager.loadRules(rules);
    }

    /**
     * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法
     *
     * 怎么使用：1. <artifactId>sentinel-annotation-aspectj</artifactId>
     *         2. 配置bean  SentinelResourceAspect
     *         (1) value: name of the Sentinel resource
     *         (2) blockHandler: name of the block exception function, empty
     *         by default(流控降级之后的处理方法，默认该方法必须声明在同一个类)
     *         (3) fallback 当接口出现了异常，就可以交给fallback指定的方法来处理
     *
     * 如果同时指定了fallback 和 blockHandler，则blockHandler优先级更高
     *
     */
    @GetMapping("/block")
    @SentinelResource(value = USER_RESOURCE_NAME
            ,fallback = "fallbackForBlock"
            ,blockHandler = "blockHandlerForBlock")
    public String block() {
        int a = 1/0;
        return "成功请求block";
    }

    public String fallbackForBlock(Throwable e) {
        e.printStackTrace();
        return "发生异常";
    }

    /**
     * 注意点：1. 方法必须为public
     *        2. 返回值和源方法要保持一致
     *        3. 可以在参数最后添加BlockException 可以区分是什么规则的处理方法
     */
    public String blockHandlerForBlock(BlockException e) {
        e.printStackTrace();
        return "block被限流";
    }

    @GetMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME,entryType = EntryType.IN
            ,blockHandler = "blockHandlerForDegrade")
    public String degrade() {
        throw new RuntimeException("异常");
    }

    public String blockHandlerForDegrade(BlockException e) {
        e.printStackTrace();
        return "降级";
    }

    @PostConstruct
    public void initDegradeRule() {
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        //设置降级规则：异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        //触发熔断异常数：2
        degradeRule.setCount(2);
        //触发熔断的最小请求数：2
        degradeRule.setMinRequestAmount(2);
        //统计时长: 单位：ms
        degradeRule.setStatIntervalMs(60*1000);
        //熔断持续时长：10s
        degradeRule.setTimeWindow(10);

        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }
}
