package org.codrim.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YYYYMMddHHmmssDateStringDeSerializer extends StdDeserializer<Date> {
    private SimpleDateFormat formatter =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public YYYYMMddHHmmssDateStringDeSerializer() {
        this(null);
    }

    public YYYYMMddHHmmssDateStringDeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
        throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
