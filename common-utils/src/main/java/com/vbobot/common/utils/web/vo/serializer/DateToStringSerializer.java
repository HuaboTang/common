package com.vbobot.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Serializer date to 'yyyy-MM-dd HH:mm:ss'
 * Created by ZhugeLiang on 03/02/2017.
 */
public class DateToStringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateFormatUtils.format(value, "yyyy-MM-dd HH:mm:ss"));
    }
}
