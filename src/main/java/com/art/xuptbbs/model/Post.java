package com.art.xuptbbs.model;

import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;

import java.time.LocalDateTime;
import java.util.List;

public class Post extends BaseModel implements Identifiable<Long> {

   private Long id;

   private String userId;

   private String title;

   private Board board;

   private Classification classification;

   private String content;

   private Long likeCount;

   private Long joinCount;

   private LocalDateTime lastActiveAt;

   private Long looked;

    private List<String> path;

    private User user;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Long joinCount) {
        this.joinCount = joinCount;
    }

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Long getLooked() {
        return looked;
    }

    public void setLooked(Long looked) {
        this.looked = looked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", board=" + board +
                ", classification=" + classification +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", joinCount=" + joinCount +
                ", lastActiveAt=" + lastActiveAt +
                ", looked=" + looked +
                ", path=" + path +
                ", user=" + user +
                '}';
    }
}
