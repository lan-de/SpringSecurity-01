package xyz.lande.demo.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import xyz.lande.demo.entity.SysUser;
import xyz.lande.demo.security.entity.LoginForm;
import xyz.lande.demo.security.exception.AccountException;
import xyz.lande.demo.service.SysUserService;
import xyz.lande.demo.utils.JwtUtils;
import xyz.lande.demo.utils.ResponseUtil;
import xyz.lande.demo.utils.Result;
import xyz.lande.demo.utils.ResultCode;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验；含认证成功和失败后执行的方法
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    /**
     * 该redisTemplate不能直接由容器注入
     *       因为TokenLoginFilter类不在spring容器内，所以redisTemplate不能直接注入
     *       该redisTemplate是通过MyWebSecurityConfig中TokenLoginFilter的构造条件注入的。
     *       也就是下面 public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate)这个方法
     */

    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        //从MyWebSecurityConfig得到redisTemplate
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        //自定义登录url
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)  {
        try {
            //从请求中读取数据
            LoginForm user = new ObjectMapper().readValue(req.getInputStream(), LoginForm.class);
            //两种抛异常方法
            //自定义AccountException
            if (user.getUserphone() == null || user.getUserphone().equals("")){
                throw new AccountException("账号为空");
            }
            //security中AuthenticationException的自雷异常
            if (user.getPassword() == null || user.getPassword().equals("")){
                throw new BadCredentialsException("密码为空");
            }
            //调方法
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserphone(), user.getPassword());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            ResponseUtil.out(res, Result.code(ResultCode.fail).message("数据读取错误"));
        }
        return null;
    }

    /**
     * 登录成功
     *      成功后创建token返回前端，并将用户权限存入redis
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SysUser user = (SysUser) auth.getPrincipal();
        String userPhone = user.getUserPhone();
        String userName = user.getUsername();
        String jwtToken = JwtUtils.getJwtToken(userPhone, userName);
        redisTemplate.opsForValue().set(userPhone,user.getRoleCodes());
        redisTemplate.opsForValue().set("token",jwtToken);
        ResponseUtil.out(response, Result.code(ResultCode.SUCCESS).message("登录成功！").data("token", jwtToken));

    }

    /**
     * 登录失败
     *      失败后提示用户登录失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, Result.code(ResultCode.fail).message(e.getMessage()));
    }




}
