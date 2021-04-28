package com.vbobot.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vbobot.common.utils.enums.EnumUtils;
import com.vbobot.common.utils.enums.EnumWithKeyDesc;
import com.vbobot.common.utils.exception.Assert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 多个枚举key,转换为key-value列表
 * Created by liang.ma on 08/12/2016.
 */
public class NumberKeysToEnumJsonSerializer<T extends Enum<T> & EnumWithKeyDesc<Number>> extends JsonSerializer<String> {
    private static final Logger logger = LoggerFactory.getLogger(NumberKeysToEnumJsonSerializer.class);

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

                final List<EnumJson> result = new ArrayList<>(values.length);
                for (String s : values) {
                    final EnumWithKeyDesc tmp = EnumUtils.enumForNumberKey(enumClass, NumberUtils.toLong(s));
                    result.add(new EnumJson(tmp.getKey(), tmp.getDesc()));
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
