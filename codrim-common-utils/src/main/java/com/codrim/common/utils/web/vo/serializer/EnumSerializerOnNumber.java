package com.codrim.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *枚举key序列化为key-value
 * Created by liang.ma on 08/12/2016.
 */
public class EnumSerializerOnNumber extends JsonSerializer<Number>{
    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException{
        Class clazz = gen.getCurrentValue().getClass();
        String filedName = gen.getOutputContext().getCurrentName();
        try {

            Field field = clazz.getDeclaredField(filedName);
            EnumTypeSpecify annotation = field.getAnnotation(EnumTypeSpecify.class);
            Class enumClass = annotation.using();
            Object[] enums = enumClass.getEnumConstants();
            Method getKey = enumClass.getMethod("getKey");
            Method getDesc = enumClass.getMethod("getDesc");
            for (Object tmp : enums) {
                int key = (Integer) getKey.invoke(tmp);
                if (key == value.intValue()) {
                    /** filed已经被写出,所以只需要写出内容即可(下文表示当前field内容为对象即:{xxx:xxx,xxx:xxx})**/
                    gen.writeStartObject();
                    gen.writeObjectField("key",getKey.invoke(tmp));
                    gen.writeObjectField("desc", getDesc.invoke(tmp));
                    gen.writeEndObject();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
