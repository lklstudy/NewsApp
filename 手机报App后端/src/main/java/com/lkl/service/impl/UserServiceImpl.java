package com.lkl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkl.domain.User;
import com.lkl.service.UserService;
import com.lkl.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public Integer addUserByEmail(Map<String, String> params) {
        Integer userNum = userMapper.selectUserNum();
        params.put("userNum","追风少年" + (userNum + 1));
        Integer res = userMapper.addUserByEmail(params);
        Integer userId = userMapper.getUserId(params);
        Integer res2 = userMapper.addUserInfo(userId);
        return res;
    }

    @Override
    public Map<String,Object> loginByEmail(User user) {
        return userMapper.loginByEmail(user);
    }

    public Integer getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }

    @Override
    public Map<String, Object> getUserAllInfo(Map<String, String> params) {
        return userMapper.getUserAllInfo(params);
    }

    @Override
    public Object follower(Map<String, Object> params) {
        if (Integer.parseInt(params.get("option").toString()) == 1){
            return userMapper.addFollower(params);
        } else if (Integer.parseInt(params.get("option").toString()) == 2) {
            return userMapper.delFollower(params);
        } else if (Integer.parseInt(params.get("option").toString()) == 3) {
            return userMapper.selectFollower(params);
        } else if (Integer.parseInt(params.get("option").toString()) == 4) {
            List<Map<String, Object>> maps = userMapper.selectFollowers(params);
            for (Map<String, Object> map :
                    maps) {
                map.put("isguanzhu", true);
                map.put("btnCss", "round margin-lr-sm cu-btn round bg-gray");
            }
            return maps;
        }

        return null;
    }

    @Override
    public Object updateUserInfo(Map<String, Object> params) {
        return userMapper.updateUserInfo(params);
    }
}




