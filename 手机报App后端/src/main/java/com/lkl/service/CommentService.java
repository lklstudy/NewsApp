package com.lkl.service;

import com.lkl.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
* @author 31033
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2024-01-11 00:02:50
*/
public interface CommentService extends IService<Comment> {


    List<Map<String, Object>> getCommentById(String newsID);

    Integer addComment(String newsID,String userID,String content);

    void likeandcollection(String newsID,  String userID, String type,String status);
}
