package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.News;
import com.lkl.service.NewsService;
import com.lkl.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 31033
* @description 针对表【news(新闻表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




