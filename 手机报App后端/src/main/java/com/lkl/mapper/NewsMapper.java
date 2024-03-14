package com.lkl.mapper;

import com.lkl.domain.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author 31033
* @description 针对表【news(新闻表)】的数据库操作Mapper
* @createDate 2024-01-11 00:02:50
* @Entity com.lkl.domain.News
*/
public interface NewsMapper extends BaseMapper<News> {

    Map<String,Object> getNewsById(String newsid,String userid);

    List<Map<String, Object>> getType();

    Integer addType(String typeName,String userID);

    Integer EditType(String typeName, String typeID);

    Integer delType(String typeID);

    Integer addNews(Map<String, String> params);

    List<Map<String, Object>> getNewsByTitle(Map<String, String> params);


    Integer delNow(Map<String, Object> params);

    Integer updateNews(Map<String, Object> params);

    List<Map<String, Object>> searchCollectionNews(Map<String, Object> params);
}




