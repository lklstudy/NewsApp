package com.lkl.mapper;

import com.lkl.domain.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
* @author 31033
* @description 针对表【news(新闻表)】的数据库操作Mapper
* @createDate 2024-01-11 00:02:50
* @Entity com.lkl.domain.News
*/
public interface NewsMapper extends BaseMapper<News> {

    Map<String,Object> getNewsById(String newsid,String userid);
}




