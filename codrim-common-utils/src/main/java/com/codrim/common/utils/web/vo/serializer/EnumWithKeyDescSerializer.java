package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumWithKeyDesc;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Json serializer for EnumWithKeyDesc
 * Created by ZhugeLiang on 14/02/2017.
 */
public class EnumWithKeyDescSerializer<Key, T extends Enum<T> & EnumWithKeyDesc<Key>> extends JsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null) {
            gen.writeString("");
        } else {
            gen.writeObject(new EnumToString<>(value.getDesc(), value.getKey()));
        }
    }
}
