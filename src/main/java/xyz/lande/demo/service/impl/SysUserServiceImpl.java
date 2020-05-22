package xyz.lande.demo.service.impl;

import xyz.lande.demo.entity.SysUser;
import xyz.lande.demo.mapper.SysUserMapper;
import xyz.lande.demo.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    //根据用户名查找用户
    @Override
    public SysUser findByPhone(String userphone) {
        SysUser user = baseMapper.findByPhone(userphone);
        return user;
    }
}
