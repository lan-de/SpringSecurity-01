package xyz.lande.demo.service.impl;

import xyz.lande.demo.entity.UserRole;
import xyz.lande.demo.mapper.UserRoleMapper;
import xyz.lande.demo.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
