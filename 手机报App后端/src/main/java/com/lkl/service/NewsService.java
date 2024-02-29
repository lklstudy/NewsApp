package com.lkl.service;

import com.lkl.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 31033
* @description 针对表【news(新闻表)】的数据库操作Service
* @createDate 2024-01-11 00:02:50
*/
public interface NewsService extends IService<News> {

    Map<String,Object> getNewsById(String newsid,String userid);
}
