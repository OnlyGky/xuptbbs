package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.vo.UserDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.awt.*;

public interface UserAttachmentService {
    BaseResponse createUserAttachment(String fromUserId, String toUserId);

    Page<UserDetailVO> selectMyfans(String id, Pageable pageable);

    Page<UserDetailVO> selectfollow(String id, Pageable pageable);

    void deleteFollow(String fromUserId, String toUserId);
}
