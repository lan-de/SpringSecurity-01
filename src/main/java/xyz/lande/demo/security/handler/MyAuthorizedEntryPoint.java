package xyz.lande.demo.security.handler;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xyz.lande.demo.utils.ResponseUtil;
import xyz.lande.demo.utils.Result;
import xyz.lande.demo.utils.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 该类用来统一处理 AuthenticationException异常
 */
@Component
public class MyAuthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        //捕捉到AuthenticationException，并获取其中自己写入的message，以json形式返回给前台
        ResponseUtil.out(response, Result.code(ResultCode.LOGIN_ERR).message("请登录后再进行操作！"));
    }
}
