package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 页面跳转
 */

@RestController
@Slf4j
public class SystemController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("/get")
    public String registry(String a, String b){
        return "get";
    }

    @PostMapping("/post")
    public String post(){
        return "post";
    }


}
