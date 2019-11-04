package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.PostDTO;
import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.model.Post;
import com.art.xuptbbs.vo.CommonPostVO;
import com.art.xuptbbs.vo.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    BaseResponse createPost(PostDTO postDTO,  String[] topic);

    Page<CommonPostVO> getPosts(Board board, String id, Pageable pageable);

    CommonPostVO selectPostDetail(Long id, String userId);

    Page<CommonPostVO> selectPostByCassification(Classification classification, String id, Pageable pageable);

    BaseResponse deletePost(Long id);

    BaseResponse updatePost(Long id, String title, String content);


    Page<CommonPostVO> selectMyPost(String id, Pageable pageable);

    Page<CommonPostVO> selectMyCollection(String id, Pageable pageable);

    Page<CommonPostVO> selectMyquestion(String id, Pageable pageable);

    BaseResponse createPostPhoto(MultipartFile file);

    BaseResponse deletePostPhoto(String path);
}
