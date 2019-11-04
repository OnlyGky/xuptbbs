package com.art.xuptbbs.vo;

import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.model.Comment;
import com.art.xuptbbs.model.Post;
import com.art.xuptbbs.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class PostVO extends CommonPostVO{


    public PostVO(Post post) {
        super(post);
    }

    private List<CommentVO> comment;


    public List<CommentVO> getComment() {
        return comment;
    }

    public void setComment(List<CommentVO> comment) {
        this.comment = comment;
    }



}
