package com.vbobot.common.utils.web.vo.dserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateDeSerializer extends StdDeserializer<Date> {
    private SimpleDateFormat formatter1 =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatter2 =
        new SimpleDateFormat("yyyy-MM-dd");

    public StringToDateDeSerializer() {
        this(null);
    }

    public StringToDateDeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
        throws IOException {
        String date = jsonparser.getText();
        Date result = null;
        if (StringUtils.isNotBlank(date)) {
            try {
                if (date.length() == 10 ) {
                    result = formatter2.parse(date);
                }else {
                    result = formatter1.parse(date);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
