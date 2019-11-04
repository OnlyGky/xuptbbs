package com.art.xuptbbs.enumeration;



import java.util.Objects;

public interface BaseEnum {

     int getvalue();

    static <T extends BaseEnum> T fromValue(Class<T> enumType, int value) {
        for (T object : enumType.getEnumConstants()) {
            if (Objects.equals(value, object.getvalue())) {
                return object;
            }
        }
        return null;
    }
}