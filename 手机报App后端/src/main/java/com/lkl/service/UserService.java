package com.lkl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lkl.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 31033
* @description 针对表【user(用户登录表)】的数据库操作Service
* @createDate 2024-01-11 00:02:50
*/
public interface UserService extends IService<User> {

    User selectUserByEmail(String email);

    Integer addUserByEmail(User user);

    Map<String,Object> loginByEmail(User user);

    Integer getUserByEmail(String email);
}
