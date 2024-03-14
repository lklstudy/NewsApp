package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.News;
import com.lkl.mapper.UserMapper;
import com.lkl.service.NewsService;
import com.lkl.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @author 31033
* @description 针对表【news(新闻表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

    @Resource
    private NewsMapper newsMapper;
    public Map<String,Object> getNewsById(String newsid,String userid){
        return newsMapper.getNewsById(newsid,userid);
    }

    @Override
    public List<Map<String, Object>> getType() {
        return newsMapper.getType();
    }

    @Override
    public Integer addType(String typeName,String userID) {
        return newsMapper.addType(typeName,userID);
    }

    @Override
    public Integer delAndEditType(String typeName, String typeID) {
        if ("".equals(typeName) || Objects.isNull(typeName)){
            return newsMapper.delType(typeID);
        }else {
            return newsMapper.EditType(typeName,typeID);
        }
    }

    @Override
    public Integer addNews(Map<String, String> params) {
        return newsMapper.addNews(params);
    }

    @Override
    public List<Map<String, Object>> getNewsByTitle(Map<String, String> params) {
        return newsMapper.getNewsByTitle(params);
    }

    @Override
    public Object delOrUpdateNews(Map<String, Object> params) {
        if (Integer.parseInt(params.get("option").toString()) == 1){
            return newsMapper.delNow(params);
        }
        if (Integer.parseInt(params.get("option").toString()) == 2){
            return newsMapper.updateNews(params);
        }
        return null;
    }

    @Override
    public Object searchCollectionNews(Map<String, Object> params) {
        return newsMapper.searchCollectionNews(params);
    }
}




