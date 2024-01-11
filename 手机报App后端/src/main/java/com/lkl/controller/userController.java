package com.lkl.controller;

import com.lkl.common.Exception.BusinessMsgEnum;
import com.lkl.common.Exception.BusinessException;
import com.lkl.domain.User;
import com.lkl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

}
