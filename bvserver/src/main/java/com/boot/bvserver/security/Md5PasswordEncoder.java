package com.boot.bvserver.security;

import com.boot.bvserver.util.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 设置 security 密码校验策略
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    /**
     * 加密策略
     *
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return Utils.md5(charSequence.toString());
    }

    /**
     * 密码比对
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence newPassword, String oldPassword) {
        return oldPassword.equals(Utils.md5(newPassword.toString()));
    }
}
