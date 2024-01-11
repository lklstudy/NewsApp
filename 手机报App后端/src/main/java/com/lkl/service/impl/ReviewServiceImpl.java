package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.Review;
import com.lkl.service.ReviewService;
import com.lkl.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

/**
* @author 31033
* @description 针对表【review(审核表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
    implements ReviewService{

}




