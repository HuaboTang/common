package com.codrim.common.utils.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * Created by liang.ma on 19/11/2016.
 */
public class BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 拷贝列表中所有对象属性到指定object并组装成列表
     * @param sourceList 源对象列表
     * @param targetType 目标对象class
     * @param <Target> 目标对像泛型
     * @param <Source> 源对象泛型
     * @return `Target` entities
     */
    public static <Target, Source> List<Target> copyProperties(List<Source> sourceList, Class<Target> targetType) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sourceList.stream().map(t1 -> copyProperties(t1, targetType)).collect(Collectors.toList());
    }

    private static <Target, Source> Target copyProperties(Class<Target> targetType, Source source) {
        try {
            Target target = targetType.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, targetType);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 拷贝源对象属性生成目标对象实例
     * @param source 源对象列表
     * @param targetType 目标对象class
     * @param <Target> 目标对像泛型
     * @param <Source> 源对象泛型
     * @return `Target` entity
     */
    public static <Target, Source> Target copyProperties(Source source, Class<Target> targetType) {
        Target target = null;
        if (source != null) {
            target = copyProperties(targetType, source);
        }
        return target;
    }

    /**
     * 拷贝源Map属性生成目标对象实例
     * @param sourceMap 源Map
     * @param targetType 目标对象class
     * @param <Target> 目标对象泛型
     * @return `Target` entity
     */
    public static <Target> Target populate(Map<String, Object> sourceMap, Class<Target> targetType) {
        if (sourceMap != null && !sourceMap.isEmpty()) {
            try {
                final Target target = org.springframework.beans.BeanUtils.instantiate(targetType);

                final PropertyDescriptor[] propertyDescriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(targetType);
                Arrays.stream(propertyDescriptors).forEach(propertyDescriptor -> {
                    final String name = propertyDescriptor.getName();
                    final Method writeMethod = propertyDescriptor.getWriteMethod();
                    Object valInMap;
                    if (sourceMap.containsKey(name)
                            && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], (valInMap = sourceMap.get(name)).getClass())) {
                        if (Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        try {
                            writeMethod.invoke(target, valInMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error(e.getMessage(), e);
                        }
                    }
                });
                return target;
            } catch (Throwable e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
