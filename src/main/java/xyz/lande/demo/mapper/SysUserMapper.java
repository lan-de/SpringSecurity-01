package xyz.lande.demo.mapper;

import xyz.lande.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    //根据用户名查找用户
    SysUser findByPhone(String userphone);
}
