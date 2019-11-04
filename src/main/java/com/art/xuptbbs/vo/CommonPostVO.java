package com.art.xuptbbs.vo;

import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.model.Post;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class CommonPostVO {

    public CommonPostVO(Post post) {
        BeanUtils.copyProperties(post, this);
        userVO = new UserVO(post.getUser());
    }


    private Long id;

    private String title;

    private Board board;

    private Classification classification;

    private String content;

    private Long likeCount;

    private Long joinCount;

    private LocalDateTime lastActiveAt;

    private Long looked;

    private int isLike;

    private int isCollection;

    private List<String> topic;

    private List<String> path;

    private UserVO userVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
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

    public Long getLooked() {
        return looked;
    }

    public void setLooked(Long looked) {
        this.looked = looked;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {

        this.path = path;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }


}
