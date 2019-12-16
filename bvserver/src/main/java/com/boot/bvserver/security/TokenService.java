package com.boot.bvserver.security;

import com.alibaba.fastjson.JSON;
import com.boot.bvserver.bean.Role;
import com.boot.bvserver.bean.User;
import com.boot.bvserver.util.Jwt;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成 token 的 service.
 */
@Getter
@Setter
@Component
public class TokenService {

    @Value("${authTokenDuration}")
    private int authTokenDuration; // 身份认证 token 的有效期，单位为秒

    @Value("${appId}")
    private String appId; // 应用的 ID

    @Value("${appKey}")
    private String appKey; // 应用的秘钥，可以定期更换

    // 生成 token
    public String generateToken(User user) {
        // Token 中保存 id, username, roles
        long expiredAt = System.currentTimeMillis() + authTokenDuration * 1000L;
        return Jwt.create(appId, appKey).expiredAt(expiredAt)
                .param("id", String.valueOf(user.getId()))
                .param("username", user.getUsername())
                .param("nickname", user.getNickname())
                .token();
    }

    // 检测 token 的有效性
    public boolean checkToken(String token) {
        return Jwt.checkToken(token, appKey);
    }

    // 从 token 中提取用户
    public User extractUser(String token) {
        if (!this.checkToken(token)) {
            return null;
        }

        try {
            // 获取 token 中保存的 id, username, roles
            Map<String, String> params = Jwt.params(token);
            Long         id = Long.parseLong(params.get("id"));
            String username = params.get("username");
            String nickname = params.get("nickname");
            List<Role> roles = new ArrayList<>();
            Role role = new Role();
            role.setCode("ROLE_admin").setName("管理员").setId(1l);
            roles.add(role);
            User user = new User();
            user.setId(id).setNickname(nickname).setUsername(username).setPassword("[protected]").setRoles(roles);
            return user;
        } catch (Exception ex) {
            return null;
        }
    }
}
