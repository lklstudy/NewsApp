package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.UserInfo;
import com.lkl.service.UserInfoService;
import com.lkl.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 31033
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:51
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




