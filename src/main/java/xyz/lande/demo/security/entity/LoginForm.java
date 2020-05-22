package xyz.lande.demo.security.entity;

import lombok.Data;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 接受前端登录传过来的json
 */
@Data
public class LoginForm {
    private String userphone;
    private String password;
}
