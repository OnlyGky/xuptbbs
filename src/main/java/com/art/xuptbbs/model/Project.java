package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class Project  extends BaseModel implements Identifiable<Long> {

    private Long id;

    private String userId;

    private String title;

    private String projectContent;

//    private String partnerContent;

    private int conpensation;

    private int type;

    private LocalDateTime lastModified;

    private User user;

    private List<ProjectLabel> label;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProjectLabel> getLabel() {
        return label;
    }

    public void setLabel(List<ProjectLabel> label) {
        this.label = label;
    }

//    @Override
//    public String toString() {
//        return "Project{" +
//                "id=" + id +
//                ", userId='" + userId + '\'' +
//                ", title='" + title + '\'' +
//                ", projectContent='" + projectContent + '\'' +
//                ", partnerContent='" + partnerContent + '\'' +
//                ", conpensation=" + conpensation +
//                ", type=" + type +
//                ", lastModified=" + lastModified +
//                '}';
//    }
}
