package com.example.controller;


import com.example.config.Result;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;



@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户功能")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("发送验证码")
    @PostMapping("code")
    public Result sendCode(@RequestParam("phone") @ApiParam("请求的手机号") String phone) {
        //发送短信验证码并保存验证码
        return userService.senCode(phone);
    }


    @ApiOperation("登录验证")
    @GetMapping("login")
    public Result login(String username,String passwd){
        Result login = userService.login(username , passwd);
        return Result.ok(login);
    }

}
