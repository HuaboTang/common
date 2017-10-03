package com.codrim.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

/**
 * null or 0 则返回空串
 * 其他:
 *      包含小数点的数字,小数点会被忽略.
 *      剩下的数字10位(尾部补3位0)或13位转为日期格式字符串
 * Created by tanghuabo on 28/11/2016.
 */
public class NumberObjectToDateStringSerializer extends JsonSerializer<Object>{

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null || "0".equals(value)) {
            gen.writeString("");
        } else {
            String s = String.valueOf(value);
            s = s.replace(".","");
            if (s.length() == 10)
               s = s + "000";
            if (s.length() != 13)
                throw new IllegalArgumentException("Error format milliSecond {" + s + "}");
            final Date date = new Date(NumberUtils.toLong(s));
            gen.writeString(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        }
    }
}


