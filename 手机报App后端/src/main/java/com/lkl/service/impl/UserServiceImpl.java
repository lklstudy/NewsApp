package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.User;
import com.lkl.service.UserService;
import com.lkl.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 31033
* @description 针对表【user(用户登录表)】的数据库操作Service实现
* @createDate 2024-01-11 00:02:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectByEmailUser(email);
    }
}




