package com.lkl.mapper;

import com.lkl.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 31033
* @description 针对表【user(用户登录表)】的数据库操作Mapper
* @createDate 2024-01-11 00:02:50
* @Entity com.lkl.domain.User
*/

public interface UserMapper extends BaseMapper<User> {

    User selectByEmailUser(String email);
}




