package com.vbobot.common.invoke.cost.time.sub;

import org.springframework.stereotype.Component;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Component
public class BeInvokeInPackageBean {
    public void invoke() throws InterruptedException {
        Thread.sleep(1000L);
    }
}
