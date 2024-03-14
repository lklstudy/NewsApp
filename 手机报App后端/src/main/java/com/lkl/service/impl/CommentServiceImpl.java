package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.Comment;
import com.lkl.mapper.UserMapper;
import com.lkl.service.CommentService;
import com.lkl.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.JstlUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @author 31033
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    private CommentMapper commentMapper;
    public List<Map<String, Object>> getCommentById(String newsID){
        return commentMapper.getCommentById(newsID);
    }

    @Override
    public Integer addComment(String newsID, String userID, String content) {
        return commentMapper.addComment(newsID,userID,content);
    }

    @Override
    public void likeandcollection(String newsID, String userID, String type, String status) {
        if (Integer.parseInt(type) == 1){
            if (Integer.parseInt(status) == 1){
                commentMapper.addlike(newsID,userID);
            }else {
                commentMapper.dellike(newsID,userID);
            }
        }else {
            if (Integer.parseInt(status) == 1){
                commentMapper.addcollection(newsID,userID);
            }else {
                commentMapper.delcollection(newsID,userID);
            }

        }
    }

}




