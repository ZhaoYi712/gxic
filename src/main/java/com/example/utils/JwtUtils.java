package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Api(tags = "创建Jwt生成/验证工具类")
public class JwtUtils {


    /**
     * 获取 token
     * @param user
     * @return
     */
    public static String getToken(User user){
        Calendar instance = Calendar.getInstance();

        //默认令牌过期时间：1天
        instance.add(Calendar.DATE,1);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId",user.getId());
        builder.withClaim("passwd",user.getPassword());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }


    /**
     * 验证token合法性
     * @param token
     * @return
     * @throws Exception
     */
    public static DecodedJWT verify(String token) throws Exception{
        if (StringUtils.isEmpty(token)){
            throw new Exception("token不能为空");
        }
        //获取登录用户真正的密码假如数据库查出来的是123456
        String passwd = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(passwd)).build();
        return build.verify(token);
    }
}
