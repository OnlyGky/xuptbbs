package com.art.xuptbbs.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProjectDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private String projectContent;

//    @NotBlank
//    private String partnerContent;

    @NotNull
    private int conpensation;

    @NotNull
    private int type;

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

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

//    public String getPartnerContent() {
//        return partnerContent;
//    }
//
//    public void setPartnerContent(String partnerContent) {
//        this.partnerContent = partnerContent;
//    }

    public int getConpensation() {
        return conpensation;
    }

    public void setConpensation(int conpensation) {
        this.conpensation = conpensation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//    @Override
//    public String toString() {
//        return "ProjectDTO{" +
//                "userId='" + userId + '\'' +
//                ", title='" + title + '\'' +
//                ", projectContent='" + projectContent + '\'' +
//                ", partnerContent='" + partnerContent + '\'' +
//                ", conpensation=" + conpensation +
//                ", type=" + type +
//
//                '}';
//    }
}
