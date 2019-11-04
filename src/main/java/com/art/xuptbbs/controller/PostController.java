package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.PostDTO;
import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.service.PostService;
import com.art.xuptbbs.vo.CommonPostVO;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Validated
@ResponseBody
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("postphoto")
    public BaseResponse postPhoto(MultipartFile file){
       return postService.createPostPhoto(file);
    }

    @DeleteMapping("postphoto")
    public BaseResponse deletePostPhoto(String path){
        return postService.deletePostPhoto(path);
    }

    @PostMapping("post")
    public BaseResponse createPost(@Validated PostDTO postDTO,

                                   @RequestParam(required = false) String[] topic){

        return postService.createPost(postDTO, topic);
    }

    /**
     *
     * @param board
     * @param id 当前用户id
     * @param page
     * @param perPage
     * @return
     */
    @GetMapping("post")
    public Page<CommonPostVO> selectPost(
            @RequestParam(required = true) Board board,
            @RequestParam(value = "id", required = false) String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<CommonPostVO> postPage;
        postPage = postService.getPosts(board, id, pageRequest);
        return postPage;
    }

    @GetMapping("post/detail")
    public CommonPostVO selectPostDetail(@RequestParam("id") Long id, @RequestParam(required = false) String userId){
        return postService.selectPostDetail(id, userId);
    }


    @GetMapping("post/classification")
    public Page<CommonPostVO> selectPostByClassification
            (

            @RequestParam(required = false) Classification classification,
             @RequestParam(value = "id", required = false) String id,
             @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
             @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<CommonPostVO> postPage;
        postPage = postService.selectPostByCassification(classification, id, pageRequest);
        return postPage;
    }

    @DeleteMapping("post")
    public BaseResponse deletePost(Long id){
       return postService.deletePost(id);
    }


    @PostMapping("post/update")
    public BaseResponse updatePost(Long id, String title, String content){
        return postService.updatePost(id, title, content);
    }

    @GetMapping("post/mypost")
    public Page<CommonPostVO> selectMyPost
            (@RequestParam(value = "id") String id,
             @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
             @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<CommonPostVO> postPage;
        postPage = postService.selectMyPost(id, pageRequest);
        return postPage;
    }

    @GetMapping("post/myquestion")
    public Page<CommonPostVO> selectMyquestion
            (@RequestParam(value = "id") String id,
             @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
             @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<CommonPostVO> postPage;
        postPage = postService.selectMyquestion(id, pageRequest);
        return postPage;
    }


    @GetMapping("post/collection")
    public Page<CommonPostVO> selectMyCollection
            (@RequestParam(value = "id", required = false) String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<CommonPostVO> postPage;
        postPage = postService.selectMyCollection(id, pageRequest);
        return postPage;
    }



}
