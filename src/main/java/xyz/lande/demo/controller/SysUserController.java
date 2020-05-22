package xyz.lande.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.lande.demo.entity.SysUser;
import xyz.lande.demo.service.SysUserService;
import xyz.lande.demo.utils.Result;
import xyz.lande.demo.utils.ResultCode;

import java.util.Collection;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    //根据用户名查找用户
    @GetMapping
    public Result findByPhone(@RequestParam String userphone){
        SysUser user = sysUserService.findByPhone(userphone);
        return Result.code(ResultCode.SUCCESS).data("user",user);
    }

    @GetMapping("/lande")
    public String lande(){
        return "hello,lande";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/test")
    public String test1(){
        return "ceshihahaha";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/hello")
    public String hello(){
        return "hellohahaha";
    }

}

