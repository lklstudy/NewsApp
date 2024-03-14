package com.lkl.controller;

import com.lkl.common.Exception.BusinessMsgEnum;
import com.lkl.common.Exception.BusinessException;
import com.lkl.domain.User;
import com.lkl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam("email") String email) {
        return userService.selectUserByEmail(email);
    }

    @GetMapping("/test")
    public String test() {
        return "userService.selectUserByEmail(email)";
    }

    @PostMapping("/addUser")
    public Integer addUser(@RequestBody Map<String, String> params) {
        return userService.addUserByEmail(params);
    }

    @PostMapping ("/login")
    public Map<String,Object> login(@RequestBody User user) {
        return userService.loginByEmail(user);
    }

    @GetMapping("/getUserByEmail")
    public Integer getUserByEmail(@RequestParam("userEmail") String userEmail) {
        System.out.println(userEmail);
        System.out.println(userService.getUserByEmail(userEmail));
        return userService.getUserByEmail(userEmail);
    }

    @PostMapping("/getUserAllInfo")
    public Map<String,Object> getUserAllInfo(@RequestBody Map<String, String> params) {
        return userService.getUserAllInfo(params);
    }
    @PostMapping("/follower")
    public Object follower(@RequestBody Map<String, Object> params) {
        return userService.follower(params);
    }

    @PostMapping("/updateUserInfo")
    public Object updateUserInfo(@RequestBody Map<String, Object> params) {
        return userService.updateUserInfo(params);
    }

}
