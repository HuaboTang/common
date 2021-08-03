package com.vbobot.common.invoke.cost.time;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Slf4j
public class InvokeCostTimeConfiguration {
    public static Collection<String> BASE_PACKAGE = null;

    @Bean
    public CostTimeMonitorInterceptor costTimeMonitorInterceptor() {
        return new CostTimeMonitorInterceptor();
    }

    @Bean
    public Advisor costTimeMonitorAdvisor(CostTimeMonitorInterceptor costTimeMonitorInterceptor) {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression());
        return new DefaultPointcutAdvisor(pointcut, costTimeMonitorInterceptor);
    }

    private String expression() {
        final String collect = BASE_PACKAGE.stream().map(pkg -> "execution(* " + pkg + "..*.*(..))")
                .collect(Collectors.joining(" || "));
        log.info("Invoke-cost-time-monitor, expression:{}", collect);
        return collect;
    }
}
