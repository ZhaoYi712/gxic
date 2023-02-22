package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.config.Result;
import com.example.entity.User;


public interface UserService extends IService<User> {

    Result senCode(String phone);

    Result login(String username , String passwd);
}
