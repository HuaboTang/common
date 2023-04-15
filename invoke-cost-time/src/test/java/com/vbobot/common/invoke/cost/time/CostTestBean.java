package com.vbobot.common.invoke.cost.time;

import com.vbobot.common.invoke.cost.time.sub.BeInvokeInPackageBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Slf4j
@Component
public class CostTestBean {

    @Resource
    BeInvokeBean beInvokeBean;
    @Resource
    BeInvokeInPackageBean beInvokeInPackageBean;

    @PostConstruct
    public void init() throws InterruptedException {
        beInvokeBean.cost();
        beInvokeInPackageBean.invoke();
        log.info("Init");
    }
}
