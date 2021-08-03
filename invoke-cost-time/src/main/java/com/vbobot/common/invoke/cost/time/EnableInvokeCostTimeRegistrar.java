package com.vbobot.common.invoke.cost.time;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author Bobo
 * @date 2021/8/3
 */
public class EnableInvokeCostTimeRegistrar implements ImportSelector {


    @Override
    @NonNull
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(EnableInvokeCostTimeMonitor.class.getName());
        if (annotationAttributes == null) {
            return new String[0];
        }

        InvokeCostTimeConfiguration.BASE_PACKAGE = getBasePackages(importingClassMetadata);

        return new String[]{InvokeCostTimeConfiguration.class.getName()};
    }

    private Collection<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        final Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableInvokeCostTimeMonitor.class.getName());
        Set<String> result = new HashSet<>();
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                result.add(pkg);
            }
        }

        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                result.add(pkg);
            }
        }

        if (result.isEmpty()) {
            final String packageName = ClassUtils
                    .getPackageName(importingClassMetadata.getClassName());
            result.add(packageName);
        }
        return result;
    }
}
