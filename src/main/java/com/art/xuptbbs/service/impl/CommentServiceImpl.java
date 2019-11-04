package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.CommentDTO;
import com.art.xuptbbs.mapper.CommentMapper;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.mapper.PostMapper;
import com.art.xuptbbs.model.Comment;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.CommentService;
import com.art.xuptbbs.vo.CommentDetailVO;
import com.art.xuptbbs.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public List<CommentDetailVO> getSonComment(Long id) {
        List<Comment> comments = commentMapper.getSonComment(id);
        List<CommentDetailVO> commentVOS = new ArrayList<>();
        if (comments != null){
            for (Comment comment : comments){

                CommentDetailVO commentDetailVO = new CommentDetailVO(comment);
                commentVOS.add(commentDetailVO);
            }
        }
        return commentVOS;
    }

    @Override
    public BaseResponse createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setLastActiveAt(LocalDateTime.now());
       if (commentMapper.createComment(comment) != 0){
           postMapper.increateJoinCount(commentDTO.getPostId());
           Notification notification = new Notification( 1L, commentDTO.getPostId(), commentDTO.getContent(),
                   commentDTO.getUserId(), commentDTO.getToUserId(), LocalDateTime.now());
           notificationMapper.saveNotification(notification);
            return BaseResponse.out(CodeEnum.SUCCESS);
       }
       return BaseResponse.out(CodeEnum.FAIL);
    }

    @Override
    public BaseResponse deleteComment(Long id, String userId) {
        commentMapper.deleteComment(id, userId);
        postMapper.decreaseJoinCount(id);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }
}
