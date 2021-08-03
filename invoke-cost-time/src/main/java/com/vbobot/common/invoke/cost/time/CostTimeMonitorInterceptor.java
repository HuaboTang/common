package com.vbobot.common.invoke.cost.time;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Slf4j
public class CostTimeMonitorInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        final long begin = System.currentTimeMillis();
        try {
            return mi.proceed();
        } finally {
            ProxyMethodInvocation pmi = (ProxyMethodInvocation) mi;
            ProceedingJoinPoint pjp = lazyGetProceedingJoinPoint(pmi);
            log.info("Cost-time, {}.{}:{}", pjp.getTarget().getClass(),
                    pjp.getSignature().getName(), System.currentTimeMillis() - begin);
        }
    }

    private ProceedingJoinPoint lazyGetProceedingJoinPoint(ProxyMethodInvocation rmi) {
        return new MethodInvocationProceedingJoinPoint(rmi);
    }
}
