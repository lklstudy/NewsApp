package com.lkl.controller;

import com.lkl.domain.User;
import com.lkl.service.CommentService;
import com.lkl.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/comment")
public class commentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/getCommentById")
    public List<Map<String,Object>> getCommentById(@RequestParam("newsID") String newsID) {
        return commentService.getCommentById(newsID);
    }

    @GetMapping("/likeandcollection")
    public void likeandcollection(@RequestParam("newsID") String newsID,@RequestParam("userID") String userID,
                                  @RequestParam("type") String type,@RequestParam("status") String status) {
        commentService.likeandcollection(newsID,userID,type,status);
    }

    @PostMapping("/addComment")
    public Integer addComment(@RequestBody Map<String, String> params) {
        return commentService.addComment(params.get("newsID"),params.get("userID"),params.get("content"));
    }





}
