package xyz.lande.demo.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @description: 该类用来统一处理 AccessDeniedException异常
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, Result.code(ResultCode.ACCESS_NOT).message("您的权限不足！"));
    }
}



