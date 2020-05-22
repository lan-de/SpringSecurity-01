package xyz.lande.demo.service.impl;

import xyz.lande.demo.entity.Role;
import xyz.lande.demo.mapper.RoleMapper;
import xyz.lande.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
