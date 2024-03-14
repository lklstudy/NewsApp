package com.lkl.mapper;

import com.lkl.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkl.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
* @author 31033
* @description 针对表【user(用户登录表)】的数据库操作Mapper
* @createDate 2024-01-11 00:02:50
* @Entity com.lkl.domain.User
*/

public interface UserMapper extends BaseMapper<User> {

    User selectByEmailUser(String email);

    Integer addUserByEmail(Map<String, String> params);

    Integer getUserId(Map<String, String> params);

    Integer addUserInfo(Integer userId);

    Map<String,Object> loginByEmail(User user);

    Integer getUserByEmail(String email);


    Map<String, Object> getUserAllInfo(Map<String, String> params);

    Integer addFollower(Map<String, Object> params);

    Integer delFollower(Map<String, Object> params);

    List<Map<String,Object>> selectFollowers(Map<String, Object> params);

    Integer selectFollower(Map<String, Object> params);

    Integer selectUserNum();

    Integer updateUserInfo(Map<String, Object> params);
}




