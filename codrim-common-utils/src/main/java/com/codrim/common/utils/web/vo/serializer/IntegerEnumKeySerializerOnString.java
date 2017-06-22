package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumUtils;
import com.codrim.common.utils.enums.EnumWithKeyDesc;
import com.codrim.common.utils.exception.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 多个Integer类型的枚举key,转换为key-value列表
 * Created by liang.ma on 08/12/2016.
 */
public class IntegerEnumKeySerializerOnString<T extends Enum<T> & EnumWithKeyDesc<Integer>> extends JsonSerializer<String> {
    private static final Logger logger = LoggerFactory.getLogger(IntegerEnumKeySerializerOnString.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (StringUtils.isBlank(value)) {
            gen.writeString("");
            return;
        }

        Class clazz = gen.getCurrentValue().getClass();
        String filedName = gen.getOutputContext().getCurrentName();
        try {
            if (StringUtils.isNotBlank(value)) {
                value = value.replaceAll(" ", "");
                final String[] values = value.split(",");
                Field field = clazz.getDeclaredField(filedName);
                EnumTypeSpecify annotation = field.getAnnotation(EnumTypeSpecify.class);
                Class<T> enumClass = (Class<T>) annotation.using();

                final List<EnumForJson> result = new ArrayList<>(values.length);
                for (String s : values) {
                    final EnumWithKeyDesc tmp = EnumUtils.enumForKey(enumClass, Integer.valueOf(s));
                    result.add(new EnumForJson(tmp.getKey(), tmp.getDesc()));
                }

                Assert.isFalse(result.isEmpty(), "指定枚举类,未配置枚举信息");
                gen.writeObject(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            gen.writeString("Error");
        }
    }

}
