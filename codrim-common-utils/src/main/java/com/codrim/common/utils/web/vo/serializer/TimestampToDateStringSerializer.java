package com.codrim.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Timestamp 序列化
 */
public class TimestampToDateStringSerializer extends JsonSerializer<Timestamp> {

    @Override
    public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value == null){
            gen.writeObject(null);
        }else{
            gen.writeString(DateFormatUtils.format(value.getTime(), "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
