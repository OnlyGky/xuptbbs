package com.art.xuptbbs.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Classification implements BaseEnum {

    全部文章(11, "全部文章"),
    产品测试(12, "产品测试"),
    产品运营(13, "产品运营"),
    其他相关(21, "其他相关"),
    Java后台(22, "Java后台"),
    web前端(23, "web前端");

    private final int value;
    private final String title;

    Classification(int value, String title) {
        this.value = value;
        this.title = title;
    }


    @Override
    public int getvalue() {
        return value;
    }

    @JsonCreator
    public static Classification fromValue(int value) {
        for (Classification classification : values()) {
            if (classification.value == value) {
                return classification;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }
}
