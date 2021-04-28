package com.vbobot.common.utils.web.vo.dserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by liang.ma on 08/12/2016.
 */
public class StringToTimestampDeSerializer extends StdDeserializer<Date> {
    private SimpleDateFormat formatter1 =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatter2 =
        new SimpleDateFormat("yyyy-MM-dd");

    public StringToTimestampDeSerializer() {
        this(null);
    }

    public StringToTimestampDeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
        throws IOException {
        String date = jsonparser.getText();
        Timestamp result = null;
        if (StringUtils.isNotBlank(date)) {
            try {
                if (date.length() == 10 ) {
                    result = new Timestamp(formatter2.parse(date).getTime());
                }else {
                    result = new Timestamp(formatter1.parse(date).getTime());
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
