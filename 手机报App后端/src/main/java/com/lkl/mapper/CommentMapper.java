package com.lkl.mapper;

import com.lkl.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author 31033
* @description 针对表【comment(评论表)】的数据库操作Mapper
* @createDate 2024-01-11 00:02:50
* @Entity com.lkl.domain.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    List<Map<String, Object>> getCommentById(String newsID);

    Integer addComment(String newsID,String userID,String content);

    Integer addcollection(String newsID, String userID);

    Integer delcollection(String newsID, String userID);

    Integer dellike(String newsID, String userID);

    Integer addlike(String newsID, String userID);
}




