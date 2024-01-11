package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.Favorite;
import com.lkl.service.FavoriteService;
import com.lkl.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author 31033
* @description 针对表【favorite(收藏表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




