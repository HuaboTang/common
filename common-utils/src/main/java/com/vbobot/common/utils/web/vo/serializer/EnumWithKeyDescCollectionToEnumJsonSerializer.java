package com.vbobot.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vbobot.common.utils.enums.EnumWithKeyDesc;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Json serializer for EnumWithKeyDesc collection
 * Created by ZhugeLiang on 14/02/2017.
 */
public class EnumWithKeyDescCollectionToEnumJsonSerializer<Key, T extends Enum<T> & EnumWithKeyDesc<Key>>
        extends JsonSerializer<Collection<T>> {
    @Override
    public void serialize(Collection<T> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (CollectionUtils.isEmpty(value)) {
            gen.writeString("");
        } else {
            gen.writeObject(value.stream().map(this::toEnumToString).collect(Collectors.toList()));
        }
    }

    private EnumToString toEnumToString(T t) {
        return new EnumToString<>(t);
    }
}
