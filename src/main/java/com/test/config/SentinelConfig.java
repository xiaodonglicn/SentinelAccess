package com.test.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelConfig {

    @PostConstruct
    public void initRules() {
        List<FlowRule> rules = new ArrayList<>();
        
        // 接口A的限流规则 - QPS=2
        FlowRule ruleA = new FlowRule();
        ruleA.setResource("apiA");
        ruleA.setGrade(RuleConstant.FLOW_GRADE_QPS);
        ruleA.setCount(1); // 每秒最多2次请求
        rules.add(ruleA);
        
        // 接口B的限流规则 - QPS=5
        FlowRule ruleB = new FlowRule();
        ruleB.setResource("apiB");
        ruleB.setGrade(RuleConstant.FLOW_GRADE_QPS);
        ruleB.setCount(1); // 每秒最多5次请求
        rules.add(ruleB);
        
        FlowRuleManager.loadRules(rules);
    }
}