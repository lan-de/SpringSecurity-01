package xyz.lande.demo.service.impl;

import xyz.lande.demo.entity.Permission;
import xyz.lande.demo.mapper.PermissionMapper;
import xyz.lande.demo.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
