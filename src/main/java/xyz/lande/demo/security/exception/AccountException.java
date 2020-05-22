package xyz.lande.demo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 自定义异常
 */
public class AccountException extends AuthenticationException {

    public AccountException(String msg) {
        super(msg);
    }
}
