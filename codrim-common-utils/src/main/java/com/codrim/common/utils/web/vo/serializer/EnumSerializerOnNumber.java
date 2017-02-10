package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumUtils;
import com.codrim.common.utils.enums.EnumWithKeyDesc;
import com.codrim.common.utils.json.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 枚举key序列化为key-value
 * Created by liang.ma on 08/12/2016.
 */
public class EnumSerializerOnNumber<T extends Enum<T> & EnumWithKeyDesc<Integer>> extends JsonSerializer<Number> {
    private static final Logger logger = LoggerFactory.getLogger(EnumSerializerOnNumber.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null) {
            gen.writeString("");
            return;
        }

        Class clazz = gen.getCurrentValue().getClass();
        String filedName = gen.getOutputContext().getCurrentName();
        try {
            Field field = clazz.getDeclaredField(filedName);
            EnumTypeSpecify annotation = field.getAnnotation(EnumTypeSpecify.class);
            Class<T> enumClass = (Class<T>) annotation.using();

            final EnumWithKeyDesc<Integer> tmp = EnumUtils.enumForKey(
                    enumClass, value.intValue());
            final EnumForJson enumForJson = new EnumForJson(tmp.getKey(), tmp.getDesc());
            gen.writeObject(enumForJson);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            gen.writeString("Error");
        }
    }
}
