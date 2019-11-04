package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.config.FileConfig;
import com.art.xuptbbs.dto.PostDTO;
import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.mapper.*;
import com.art.xuptbbs.model.Answer;
import com.art.xuptbbs.model.Comment;
import com.art.xuptbbs.model.Post;
import com.art.xuptbbs.model.PostTopic;
import com.art.xuptbbs.service.PostService;
import com.art.xuptbbs.util.PhotoUtil;
import com.art.xuptbbs.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    PostMapper postMapper;

    @Autowired
    PostPhotoMapper postPhotoMapper;

    @Autowired
    PostTopicMapper postTopicMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    PostLikeMapper postLikeMapper;

    @Autowired
    PostCollectionMapper postCollectionMapper;

    @Autowired
    AnswerMapper answerMapper;

    //添加图片
    @Override
    public BaseResponse createPostPhoto(MultipartFile file) {
        if (file != null){
            String path = PhotoUtil.savePhoto(file, fileConfig.getPostUploadFolder());
            return ResponseData.out(CodeEnum.SUCCESS, "http://yugengkai.top:8080/xuptbbs/images/post/"+path);
        }else {
            return BaseResponse.out(CodeEnum.PHOTO_FAIL);
        }
    }

    //删除图片
    @Override
    public BaseResponse deletePostPhoto(String path) {
        String prePath = fileConfig.getPostUploadFolder();
       path =  path.replaceFirst( "http://yugengkai.top:8080/xuptbbs/images/post/",prePath );

        if (PhotoUtil.deletePhoto(path))
            return BaseResponse.out(CodeEnum.SUCCESS);
        return BaseResponse.out(CodeEnum.FAIL);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse createPost(PostDTO postDTO, String[] topics) {
        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);
        post.setLastActiveAt(LocalDateTime.now());
        post.setCreatedAt(LocalDateTime.now());
        postMapper.createPost(post);
        Long id = post.getId();

        if (topics != null && topics.length > 0)
            for (String topic : topics) {
                postTopicMapper.saveTopic(id, topic);
            }
        return BaseResponse.out(CodeEnum.SUCCESS);
    }


    @Override
    public Page<CommonPostVO> getPosts(Board board, String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Post> post = postMapper.selectByBoard(board);
        List<CommonPostVO> commonPostVOS = new ArrayList<>();

        List<PostTopic> postTopics = this.selectTopic();

        repair(commonPostVOS, postTopics, post, id);

        var pageInfo = new PageInfo<>(commonPostVOS);
        return new PageImpl<>(commonPostVOS, pageable, pageInfo.getTotal());
    }


    @Override
    public CommonPostVO selectPostDetail(Long id, String userId) {

        Post post = postMapper.selectPostDetail(id);
        postMapper.incermentLook(id);
        PostVO postVO = null;
        PostQuestionVO postQuestionVO = null;
        if (post.getBoard().getvalue() == 2) { //判断是不是问题
            List<Answer> answers = answerMapper.selectAnswerById(post.getId());
            List<AnswerVO> answerVOS = new ArrayList<>();
            if (null != answers) {
                for (Answer answer : answers)
                    answerVOS.add(new AnswerVO(answer));
            }
            postQuestionVO = new PostQuestionVO(post);
            postQuestionVO.setAnswerVOList(answerVOS);
        } else {
            List<Comment> list = commentMapper.selectCommentById(id);
            List<CommentVO> vos = new ArrayList<>();

            if (null != list)
                for (Comment comment : list) {
                    vos.add(new CommentVO(comment.getId(), comment.getContent(),
                            comment.getParentId(), comment.getLastActiveAt()));
                }

            BeanUtils.copyProperties(list, vos);
            postVO = new PostVO(post);
            postVO.setComment(vos);
        }
        if (userId != null) {
            List<Long> postLike = postLikeMapper.selectPostIdByUserId(userId);
            List<Long> postCollection = postCollectionMapper.selectPostIdByUserId(userId);
            if (postLike.contains(id))
                if (null != postVO) postVO.setIsLike(1);
                else postQuestionVO.setIsLike(1);
            if (postCollection.contains(id))
                if (null != postVO) postVO.setIsCollection(1);
                else postQuestionVO.setIsCollection(1);
        }
        List<String> topic = postTopicMapper.selectOneTopic(id);
        CommonPostVO commonPostVO = postVO != null ? postVO : postQuestionVO;
        commonPostVO.setTopic(topic);
        return commonPostVO;
    }

    @Override
    public Page<CommonPostVO> selectPostByCassification(Classification classification, String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Post> post = null;

        post = postMapper.selectPostByCassification(classification);
        List<CommonPostVO> commonPostVOS = new ArrayList<>();

        List<PostTopic> postTopics = this.selectTopic();

        repair(commonPostVOS, postTopics, post, id);

        var pageInfo = new PageInfo<>(commonPostVOS);
        return new PageImpl<>(commonPostVOS, pageable, pageInfo.getTotal());
    }

    @Override
    public BaseResponse deletePost(Long id) {
        postMapper.deletePost(id);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public BaseResponse updatePost(Long id, String title, String content) {

        postMapper.updatePost(id, title, content, LocalDateTime.now());
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public Page<CommonPostVO> selectMyPost(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Post> post = null;

        post = postMapper.selectMyPost(id);
        List<CommonPostVO> commonPostVOS = new ArrayList<>();
        List<PostTopic> postTopics = this.selectTopic();

        repair(commonPostVOS, postTopics, post, id);

        var pageInfo = new PageInfo<>(commonPostVOS);
        return new PageImpl<>(commonPostVOS, pageable, pageInfo.getTotal());
    }

    @Override
    public Page<CommonPostVO> selectMyCollection(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Post> post = null;
        post = postMapper.selectMyCollection(id);
        List<CommonPostVO> commonPostVOS = new ArrayList<>();

        List<PostTopic> postTopics = this.selectTopic();

        repair(commonPostVOS, postTopics, post, id);

        var pageInfo = new PageInfo<>(commonPostVOS);
        return new PageImpl<>(commonPostVOS, pageable, pageInfo.getTotal());
    }


    @Override
    public Page<CommonPostVO> selectMyquestion(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Post> post = null;

        post = postMapper.selectMyquestion(id);
        List<CommonPostVO> commonPostVOS = new ArrayList<>();

        List<PostTopic> postTopics = this.selectTopic();

        repair(commonPostVOS, postTopics, post, id);

        var pageInfo = new PageInfo<>(commonPostVOS);
        return new PageImpl<>(commonPostVOS, pageable, pageInfo.getTotal());
    }

    public List<PostTopic> selectTopic() {
        return postTopicMapper.selectTopic();
    }

    /**
     * 判断是否已经关注，收藏，及获取topic
     */
    public void repair(List<CommonPostVO> commonPostVOS,
                       List<PostTopic> postTopics, List<Post> posts, String id) {
        List<Long> postLike = null;
        List<Long> postCollection = null;
        if (null != id) {
            postLike = postLikeMapper.selectPostIdByUserId(id);
            postCollection = postCollectionMapper.selectPostIdByUserId(id);
        }

        for (Post post : posts) {
            CommonPostVO common = new CommonPostVO(post);
            if (null != id) {
                if (postLike.contains(post.getId()))
                    common.setIsLike(1);
                if (postCollection.contains(post.getId()))
                    common.setIsCollection(1);
            }
            List<String> topic = new ArrayList<>();
            for (PostTopic postTopic : postTopics) {
                if (post.getId() == postTopic.getPostId())
                    topic.add(postTopic.getTopic());
            }
            common.setTopic(topic);
            commonPostVOS.add(common);
        }
    }
}
