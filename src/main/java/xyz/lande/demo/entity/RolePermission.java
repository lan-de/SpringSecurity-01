package xyz.lande.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RolePermission对象", description="角色权限表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色权限编号")
    @TableId(value = "role_permission_id", type = IdType.ID_WORKER_STR)
    private String rolePermissionId;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "权限编号")
    private String permissionId;

    @ApiModelProperty(value = "角色权限状态")
    private String rolePermissionStatus;


}
