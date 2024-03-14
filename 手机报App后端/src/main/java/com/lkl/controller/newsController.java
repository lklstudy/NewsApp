package com.lkl.controller;


import com.lkl.domain.News;
import com.lkl.domain.User;
import com.lkl.service.NewsService;
import com.lkl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/news")
public class newsController {
    @Resource
    private NewsService newsService;

    @GetMapping("/getNewsById")
    public Map<String,Object> getNewsById(@RequestParam("newsID") String newsid,@RequestParam("userID") String userid) {
        return newsService.getNewsById(newsid,userid);
    }

    @GetMapping("/getType")
    public List<Map<String,Object>> getType() {
        return newsService.getType();
    }

    @GetMapping("/addType")
    public Integer addType(@RequestParam("typeName") String typeName,@RequestParam("userID") String userID) {
        return newsService.addType(typeName,userID);
    }

    @GetMapping("/delAndEditType")
    public Integer delAndEditType(@RequestParam("typeName") String typeName,@RequestParam("typeID") String typeID) {
        return newsService.delAndEditType(typeName,typeID);
    }

    @PostMapping("/addNews")
    public Integer addNews(@RequestBody Map<String, String> params) {
        return newsService.addNews(params);
    }

    @PostMapping("/getNewsByTitle")
    public List<Map<String,Object>> getNewsByTitle(@RequestBody Map<String, String> params) {
        return newsService.getNewsByTitle(params);
    }

    @PostMapping("/delOrUpdateNews")
    public Object delOrUpdateNews(@RequestBody Map<String, Object> params) {
        return newsService.delOrUpdateNews(params);
    }

    @PostMapping("/searchCollectionNews")
    public Object searchCollectionNews(@RequestBody Map<String, Object> params) {
        return newsService.searchCollectionNews(params);
    }
}
