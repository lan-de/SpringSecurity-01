package xyz.lande.demo.service;

import xyz.lande.demo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
public interface SysUserService extends IService<SysUser> {

    //根据用户名查找用户
    SysUser findByPhone(String userphone);

}
