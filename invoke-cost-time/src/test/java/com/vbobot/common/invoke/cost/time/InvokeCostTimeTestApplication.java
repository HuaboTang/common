package com.vbobot.common.invoke.cost.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@EnableInvokeCostTimeMonitor("com.vbobot.common.invoke.cost.time.sub")
@SpringBootApplication
public class InvokeCostTimeTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvokeCostTimeTestApplication.class, args);
    }
}
