package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.config.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.Regexutils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${Login.username}")
    private String readUsername;

    @Value("${Login.password}")
    private String readPasswd;

    /**
     * 生成及发送验证码
     * @param phone
     * @return
     */
    @Override
    public Result senCode(String phone) {
        // 1.校验手机号码
        if (Regexutils.isPhoneInvalid(phone)){
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误！");
        }

        // 3.符合，则生成6位验证码
        String code = RandomUtil.randomNumbers(6);

        // 4.保存验证码到redis
        stringRedisTemplate.opsForValue().set("login:" + phone, code,5, TimeUnit.MINUTES);

        // 5.发送验证码
        log.debug("发送验证码成功，验证码：{}",code);

        return Result.ok();
    }



    @Override
    public Result login(String username , String passwd) {
        if (username.equals(readUsername) && passwd.equals(readPasswd)){
            User user = new User();
            user.setNickName(username);
            user.setPassword(passwd);
            return Result.ok(JwtUtils.getToken(user));
        }
        return Result.fail("登录失败！账号或者密码不对！");
    }
}
