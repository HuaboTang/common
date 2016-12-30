package com.codrim.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 谨慎执行,耗时超长
 */
public class IdGeneraterTest {
	private static final Logger logger = LoggerFactory.getLogger(IdGeneraterTest.class);

    /**
     * 验证订单号生成是否会重复和耗时(单线程)
     */
    @Test
    public void testGenerateOrder() {
        List<String> orderList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            String orderId = IdGenerater.generateOrder();
            if (orderList.contains(orderId)) {
                logger.info("orderId:{}", orderId);
                logger.info("orderList:{},list:{}", new Object[] { orderList.size(), orderList });
                Assert.fail("订单号重复！");
            }
            orderList.add(orderId);
        }
        logger.info("generateOrder cost:{}", System.currentTimeMillis() - startTime);
    }

    /**
     * 验证订单号生成是否会重复和耗时(多线程)
     */
    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        long startTime = System.currentTimeMillis();
        // 模拟1000个并发请求
        for (int j = 0; j < 100; j++) {
            new Thread(new ThreadTest(list)).start();
        }
        logger.info("testGenerateOrderMultiplied cost:{}", System.currentTimeMillis() - startTime);
    }

    /**
     * 测试线程
     * 
     */
    private static class ThreadTest implements Runnable {

        private List<String> list;

        ThreadTest(List<String> list) {
            this.list = list;
        }

		public void run() {
			for (int i = 0; i < 1; i++) {
				String orderId = IdGenerater.generateOrder();
				if (list.contains(orderId)) {
					logger.error("订单号重复！");
					break;
				}
				list.add(orderId);
			}
		}
    }
}
