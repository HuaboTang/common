package com.codrim.common.utils.bean;

import com.codrim.common.utils.enums.EnumWithKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;
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

    /**
     * 拷贝列表中所有对象属性到指定object并组装成列表(支持枚举值到枚举类转换[枚举类必须实现EnumWithKey接口])
     * @param sourceList 源对象列表
     * @param targetType 目标对象class
     * @param <Target> 目标对像泛型
     * @param <Source> 源对象泛型
     * @return `Target` entities
     */
    public static <Target, Source> List<Target> copyPropertiesAndConvertKeyToEnum(List<Source> sourceList, Class<Target> targetType) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sourceList.stream().map(t1 -> copyPropertiesAndConvertKeyToEnum(t1, targetType)).collect(Collectors.toList());
    }

    private static <Target, Source> Target copyProperties(Class<Target> targetType, Source source, boolean isConvertKeyToEnum) {
        try {
            Target target = targetType.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target);
            if (isConvertKeyToEnum)
                convertKeyToEnum(targetType, source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @descr:源对象和目标对象之间的枚举值到枚举类转换
     * @param targetType
     * @param source
     * @param target
     * @return void
     * @author liang.ma
     * @date 2017-03-17 13:18
     */
    private static <Target, Source> void convertKeyToEnum(Class<Target> targetType, Source source, Target target)
        throws IllegalAccessException {

        Class sourceClass = source.getClass();
        Field[] targetFields = targetType.getDeclaredFields();
        Field[] sosurceFields = sourceClass.getDeclaredFields();
        List<String> sourceFieldNams = Arrays.stream(sosurceFields).map(m -> m.getName()).collect(Collectors.toList());
        for (Field targetField : targetFields) {
            Object targetFidldType = targetField.getType();
            String targetFiledName = targetField.getName();
            if(sourceFieldNams.contains(targetFiledName) &&  EnumWithKey.class.isAssignableFrom((Class) targetFidldType)) {
                try {
                    Field sourceField = sourceClass.getDeclaredField(targetFiledName);
                    if (sourceField != null) {
                        Iterator iterator = EnumSet.allOf((Class)targetFidldType).iterator();
                        sourceField.setAccessible(true);
                        while (iterator.hasNext()) {
                            EnumWithKey tmp = (EnumWithKey)iterator.next();
                            if (tmp.getKey().equals(sourceField.get(source))){
                                targetField.setAccessible(true);
                                targetField.set(target,tmp);
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
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
            target = copyProperties(targetType, source, false);
        }
        return target;
    }


    /**
     * 拷贝源对象属性生成目标对象实例(支持枚举值到枚举类转换[枚举类必须实现EnumWithKey接口])
     * @param source 源对象列表
     * @param targetType 目标对象class
     * @param <Target> 目标对像泛型
     * @param <Source> 源对象泛型
     * @return `Target` entity
     */
    public static <Target, Source> Target copyPropertiesAndConvertKeyToEnum(Source source, Class<Target> targetType) {
        Target target = null;
        if (source != null) {
            target = copyProperties(targetType, source, true);
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
