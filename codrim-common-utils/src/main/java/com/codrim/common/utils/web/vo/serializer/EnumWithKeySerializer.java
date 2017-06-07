package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumWithKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Json serializer for EnumWithKey
 */
public class EnumWithKeySerializer<Key, T extends Enum<T> & EnumWithKey<Key>> extends JsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null) {
            gen.writeString("");
        } else {
            gen.writeObject(value.getKey());
        }
    }
}
