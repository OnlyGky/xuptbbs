package com.art.xuptbbs.converter;


import com.art.xuptbbs.enumeration.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class IntegerToBaseEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum<>(targetType);
    }

    private class IntegerToEnum<T extends BaseEnum> implements Converter<Integer, T> {

        private final Class<T> enumType;

        IntegerToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer source) {
            return BaseEnum.fromValue(enumType, source);
        }
    }
}
