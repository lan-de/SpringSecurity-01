package xyz.lande.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

/**
 * @author lande
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户手机号码 ")
    private String userPhone;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户最近一次登录时间")
    private String userLastLoginTime;

    @ApiModelProperty(value = "用户注册时间")
    private String userCreateTime;

    @ApiModelProperty(value = "用户状态，0正常，-1删除")
    private Integer userStatus;

    @ApiModelProperty(value = "用户的角色列表")
    private List<String> roleCodes;

    //重写UserDetails中的方法，得到权限列表，权限列表中存储的是角色名
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //将用户的角色以SimpleGrantedAuthority的形式存入authorities中
        for(String roleCode : roleCodes) {
            if(StringUtils.isEmpty(roleCode)) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleCode);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
