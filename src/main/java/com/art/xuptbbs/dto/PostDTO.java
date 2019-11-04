package com.art.xuptbbs.dto;

import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostDTO {



    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Board board;

    @NotNull
    private Classification classification;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
}
