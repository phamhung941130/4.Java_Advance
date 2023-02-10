package com.vti.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentTypeConvert implements AttributeConverter<Type, String> {
    @Override
    public String convertToDatabaseColumn(Type type) {
        return type.getCode();
    }

    @Override
    public Type convertToEntityAttribute(String code) {
        return Type.toType(code);
    }
}