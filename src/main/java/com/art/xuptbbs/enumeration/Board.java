package com.art.xuptbbs.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Board implements BaseEnum {
    ARTICLE(1, "文章"),
    QUESTION(2, "问答"),
    PROJECT(3, "做项目"),
    PRACTICE(4, "实习专栏");

    private final int value;
    private final String title;

    Board(int value, String title) {
        this.value = value;
        this.title = title;
    }

    @Override
    public int getvalue() {
        return value;
    }

    @JsonCreator
    public static Board fromValue(int value) {
        for (Board board : values()) {
            if (board.value == value) {
                return board;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }
}
