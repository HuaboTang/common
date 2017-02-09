package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.exception.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 多个枚举key,转换为key-value列表
 * Created by liang.ma on 08/12/2016.
 */
public class EnumSerializerOnString extends JsonSerializer<String>{
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException{
        Class clazz = gen.getCurrentValue().getClass();
        String filedName = gen.getOutputContext().getCurrentName();
        try {
            gen.writeStartArray();/** 该字段对应内容为数组 即:[ **/
            if (StringUtils.isNotBlank(value)){
                value = value.replaceAll(" ","");
                Field field = clazz.getDeclaredField(filedName);
                EnumTypeSpecify annotation = field.getAnnotation(EnumTypeSpecify.class);
                Class enumClass = annotation.using();
                Object[] enums = enumClass.getEnumConstants();
                Method getKey = enumClass.getMethod("getKey");
                Method getDesc = enumClass.getMethod("getDesc");
                Map<Integer,String> keyDescMap = new HashMap<>();
                for (Object tmp : enums) {
                    keyDescMap.put((int)getKey.invoke(tmp), (String)getDesc.invoke(tmp));
                }
                Assert.isFalse(keyDescMap.isEmpty(), "指定枚举类,未配置枚举信息");
                String[] keys = value.split(",");
                int key;
                for (String stringKey : keys) {
                    key = Integer.parseInt(stringKey);
                    String desc = keyDescMap.get(key);
                    if (desc != null) {
                        /** filed已经被写出,所以只需要写出内容即可(下文表示当前field内容为对象即:{xxx:xxx,xxx:xxx})**/
                        gen.writeStartObject();
                        gen.writeNumberField("key", key);
                        gen.writeObjectField("desc", desc);
                        gen.writeEndObject();
                    }
                }
            }
            gen.writeEndArray();/** 数据结尾 即:] **/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
