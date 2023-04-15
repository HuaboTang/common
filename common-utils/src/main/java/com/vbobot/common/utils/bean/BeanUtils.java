package com.vbobot.common.utils.bean;

import com.vbobot.common.utils.enums.EnumWithKey;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

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
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sourceList.stream().map(t1 -> copyProperties(t1, targetType)).collect(Collectors.toList());
    }

    /**
     * 拷贝列表中所有对象属性到指定object并组装成列表(支持枚举值到枚举类转换[枚举类必须实现EnumWithKey接口])
     * @param sourceCollections 源对象结合
     * @param targetType 目标对象class
     * @param <Target> 目标对像泛型
     * @param <Source> 源对象泛型
     * @return `Target` entities
     */
    public static <Target, Source> List<Target> copyPropertiesAndConvertKeyToEnum(Collection<Source> sourceCollections, Class<Target> targetType) {
        if (CollectionUtils.isEmpty(sourceCollections)) {
            return null;
        }
        return sourceCollections.stream().map(t1 -> copyPropertiesAndConvertKeyToEnum(t1, targetType)).collect(Collectors.toList());
    }

    private static <Target, Source> Target copyProperties(Class<Target> targetType, Source source, boolean isConvertKeyToEnum) {
        try {
            Target target = targetType.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target);
            if (isConvertKeyToEnum) {
                convertKeyToEnum(targetType, source, target);
            }
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 源对象和目标对象之间的枚举值到枚举类转换
     *
     * @param targetType 转换结果类型
     * @param source 转换源对象
     * @param target 转换结果对象
     * @author liang.ma
     * @date 2017-03-17 13:18
     */
    private static <Target, Source> void convertKeyToEnum(Class<Target> targetType, Source source, Target target)
        throws IllegalAccessException {

        Class sourceClass = source.getClass();
        List<Field> targetFields = getAllFields(targetType);
        List<Field> sourceFields = getAllFields(sourceClass);
        List<String> sourceFieldNames = sourceFields.stream().map(Field::getName).collect(Collectors.toList());
        for (Field targetField : targetFields) {
            Object targetFieldType = targetField.getType();
            String targetFiledName = targetField.getName();
            if(sourceFieldNames.contains(targetFiledName) &&  EnumWithKey.class.isAssignableFrom((Class) targetFieldType)) {
                Field sourceField =
                    sourceFields.stream().filter(field -> field.getName().equals(targetFiledName)).findFirst()
                        .orElse(null);
                if (sourceField != null) {
                    Iterator iterator = EnumSet.allOf((Class) targetFieldType).iterator();
                    sourceField.setAccessible(true);
                    while (iterator.hasNext()) {
                        EnumWithKey tmp = (EnumWithKey)iterator.next();
                        if (tmp.getKey().equals(sourceField.get(source))){
                            targetField.setAccessible(true);
                            targetField.set(target,tmp);
                        }
                    }
                }
            }
        }
    }

    private static Field getField(Class sourceClass, String fieldName) {
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (sourceClass != null) {
            Field[] fieldList = sourceClass.getDeclaredFields();
            for (Field field : fieldList) {
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }
            //得到父类,然后赋给自己
            sourceClass = sourceClass.getSuperclass();
        }
        return null;
    }


    private static List<Field> getAllFields(Class sourceClass) {
        List<Field> allField = new ArrayList<>();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (sourceClass != null) {
            allField.addAll(Arrays.asList(sourceClass .getDeclaredFields()));
            //得到父类,然后赋给自己
            sourceClass = sourceClass.getSuperclass();
        }
        return allField;
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
                final Target target = targetType.newInstance();

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

    public static <Target> Target copyPropertiesAndConvertEnumToKey(Object source, Class<Target> targetType) {
        try {
            Target target = targetType.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target);

            convertEnumToKey(targetType, source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private static <Target, Source> void convertEnumToKey(Class<Target> targetType, Source source, Target target)
            throws IllegalAccessException {
        Class sourceClass = source.getClass();

        List<Field> targetFields = getAllFields(targetType);
        List<Field> sourceFields = getAllFields(sourceClass);

        List<String> targetFieldNames = targetFields.stream().map(Field::getName).collect(Collectors.toList());

        for (Field sourceField : sourceFields) {
            Object sourceFieldType = sourceField.getType();
            String sourceFieldName = sourceField.getName();
            if(targetFieldNames.contains(sourceFieldName)
                    &&  EnumWithKey.class.isAssignableFrom((Class) sourceFieldType)) {
                Field targetField = targetFields.stream()
                        .filter(field -> field.getName().equals(sourceFieldName))
                        .findFirst()
                        .orElse(null);
                if (targetField != null) {
                    sourceField.setAccessible(true);
                    final EnumWithKey e = (EnumWithKey) sourceField.get(source);
                    if (e != null) {
                        targetField.setAccessible(true);
                        targetField.set(target, e.getKey());
                    }
                }
            }
        }
    }
}
