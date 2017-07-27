package com.codrim.common.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * Created by Liang.Zhuge on 27/07/2017.
 */
public class NumberUtils {

    /**
     * 两个数相除,并返回保留二位小数的百分值
     * @param dividend 被除数
     * @param divisor 除数
     * @return 保留二位小数的百分值
     */
    public static String toPercent(Integer dividend, Integer divisor) {
        if (dividend==null || divisor==null || dividend==0 || divisor==0) {
            return "0.00%";
        } else {
            return new BigDecimal(dividend).multiply(new BigDecimal(100))
                    .divide(new BigDecimal(divisor), 2, RoundingMode.HALF_UP).toString() + "%";
        }
    }

    /**
     * 将对象转换为数值,参数为`null`时,返回0
     * @param oInt 待拆包整数
     * @return 整数值,或0
     */
    public static int toInt(Integer oInt) {
        return toInt(oInt, 0);
    }

    /**
     * 将对象转换为数值,参数为`null`时,返回`defaultValue`
     * @param oInt 待拆包整数
     * @param defaultValue 当`oInt`为`null`时,返回的值
     * @return 整数值,或`defaultValue`
     */
    public static int toInt(Integer oInt, int defaultValue) {
        return oInt == null ? defaultValue : oInt;
    }
}
