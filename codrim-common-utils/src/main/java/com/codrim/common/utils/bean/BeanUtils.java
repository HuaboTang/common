package com.codrim.common.utils.bean;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liang.ma on 19/11/2016.
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

    /**
     * 拷贝列表中所有对象属性到指定object并组装成列表
     * @param origList 源对象列表
     * @param t2Clazz 目标对象class
     * @param <T2> 目标对像泛型
     * @param <T1> 源对象泛型
     * @return
     */
    public static <T2 extends java.lang.Object, T1 extends java.lang.Object> List<T2>  copyProperties(List<T1> origList ,Class<T2> t2Clazz) {
        List<T2> destList = null;
        if (CollectionUtils.isNotEmpty(origList)) {

            destList = new ArrayList<T2>();
            for (T1 t1 : origList) {
                if (t1 == null) continue;
                T2 t2 = copyProperties(t2Clazz, t1);
                if (t2 != null)
                    destList.add(t2);
            }
        }
        return destList;
    }

    private static <T2 extends java.lang.Object, T1 extends java.lang.Object> T2 copyProperties(
        Class<T2> t2Clazz, T1 origObj) {
        T2 t2 = null;
        try {
            t2 = t2Clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.copyProperties(t2, origObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t2;
    }


    /**
     * 拷贝源对象属性生成目标对象实例
     * @param origObj 源对象列表
     * @param t2Clazz 目标对象class
     * @param <T2> 目标对像泛型
     * @param <T1> 源对象泛型
     * @return
     */
    public static <T2 extends java.lang.Object, T1 extends java.lang.Object> T2 copyProperties(T1 origObj ,Class<T2> t2Clazz) {
        T2 t2 = null;
        if (origObj != null) {
            t2 = copyProperties(t2Clazz,origObj);
        }
        return t2;
    }

    /**
     * 拷贝源Map属性生成目标对象实例
     * @param origMap 源Map
     * @param t1Clazz 目标对象class
     * @param <T1> 目标对象泛型
     * @return
     */
    public static <T1> T1 populate(Map<String,Object> origMap ,Class<T1> t1Clazz) {
        T1 t1 = null;
        if (origMap != null && !origMap.isEmpty()) {
            try {
                t1 = t1Clazz.newInstance();
                org.apache.commons.beanutils.BeanUtils.populate(t1,origMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return t1;
    }
}
