package xyz.lande.demo.security.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import xyz.lande.demo.utils.JwtUtils;
import xyz.lande.demo.utils.ResponseUtil;
import xyz.lande.demo.utils.Result;
import xyz.lande.demo.utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 处理注销请求
 */
public class MyLogoutHandler implements LogoutHandler {

    private RedisTemplate redisTemplate;

    public MyLogoutHandler(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        String userPhone = JwtUtils.getUserPhoneByJwtToken(request);
        if (token != null){
            //清除缓存里的信息
            redisTemplate.delete(userPhone);
            redisTemplate.delete("token");
        }
        ResponseUtil.out(response, Result.code(ResultCode.SUCCESS).message("退出成功"));

    }
}
