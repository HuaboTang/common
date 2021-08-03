package com.vbobot.common.invoke.cost.time;

import org.springframework.stereotype.Component;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Component
public class BeInvokeBean {
    public void cost() throws InterruptedException {
        Thread.sleep(1000L);
    }
}
