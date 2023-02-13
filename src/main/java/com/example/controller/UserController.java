package com.example.controller;

import com.example.config.R;
import com.example.entity.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

/*    @Resource
    private UserService userService;

    @PostMapping("/create")
    public R<User> save(@RequestBody User users){
        log.info("注册用户开始...");

        users.setCreate_time(LocalDateTime.now());

        userService.save(users);
        return R.success(users);
    }*/
}
