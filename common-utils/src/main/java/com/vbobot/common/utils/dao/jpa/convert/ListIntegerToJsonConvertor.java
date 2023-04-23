package com.vbobot.common.utils.dao.jpa.convert;

import com.vbobot.common.utils.json.JsonMapper;
import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author Bobo
 * @date 2023/4/23
 */
public class ListIntegerToJsonConvertor implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return attribute == null ? null : JsonMapper.getInstance().toJson(attribute);
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return dbData == null ? null : JsonMapper.getInstance().fromJson(dbData, List.class, Integer.class);
    }
}
