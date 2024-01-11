package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.Likes;
import com.lkl.service.LikesService;
import com.lkl.mapper.LikesMapper;
import org.springframework.stereotype.Service;

/**
* @author 31033
* @description 针对表【likes(点赞表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{

}




