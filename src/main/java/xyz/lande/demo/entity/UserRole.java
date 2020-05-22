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
 * 用户角色表
 * </p>
 *
 * @author lande
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserRole对象", description="用户角色表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户角色编号")
    @TableId(value = "user_role_id", type = IdType.ID_WORKER_STR)
    private String userRoleId;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "用户角色状态，0正常，-1删除")
    private String userRoleStatus;


}
