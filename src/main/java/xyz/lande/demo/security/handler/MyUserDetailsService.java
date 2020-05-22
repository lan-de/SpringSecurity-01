package xyz.lande.demo.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.lande.demo.entity.SysUser;
import xyz.lande.demo.security.exception.AccountException;
import xyz.lande.demo.service.SysUserService;



/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 自定义userDetailsService - 认证用户详情
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    /***
     * 根据账号获取用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String userphone) throws UsernameNotFoundException {
        //调用userService得到SysUser
        SysUser user = userService.findByPhone(userphone);
        if (user == null){
            throw new AccountException("账号或密码错误");
        }
        //SysUser继承了UserDetails接口，可直接返回
        return user;
    }
}
