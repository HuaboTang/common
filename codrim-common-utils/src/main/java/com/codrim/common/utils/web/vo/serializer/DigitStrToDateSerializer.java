package com.codrim.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

/**
 * null or "" 则返回空串
 * 其他:
 *      包含小数点的数字,小数点会被忽略.
 *      剩下的数字10位(尾部补3位0)或13位转为日期格式字符串
 * Created by liang.ma on 02/12/2016.
 */
public class DigitStrToDateSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
        if (StringUtils.isBlank(value)) {
            gen.writeString("");
        } else {
            value = value.replace(".","");
            if (value.length() == 10)
                value = value + "000";
            if (value.length() != 13 || !NumberUtils.isNumber(value))
                throw new IllegalArgumentException("Error format milliSecond {" + value + "}");
            final Date date = new Date(NumberUtils.toLong(value));
            gen.writeString(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
