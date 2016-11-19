package org.codrim.common.utils.bean;

import org.apache.commons.collections.CollectionUtils;
import org.omg.CORBA.Object;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
            T2 t2 = null;
            destList = new ArrayList<T2>();
            for (T1 t1 : origList) {
                try {
                    t2 = t2Clazz.newInstance();
                    org.apache.commons.beanutils.BeanUtils.copyProperties(t2, t1);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (t2 != null)
                    destList.add(t2);
            }
        }
        return destList;
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


}
