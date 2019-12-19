package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
public class User implements UserDetails {

    private Long id;
    private String username;
    private String nickname;
    private String password;
    private boolean enabled;
    private String picture;
    private List<Role> roles;

    // 用户权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null || roles.size() <= 0){
            return null;
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role r : roles){
            authorities.add(new SimpleGrantedAuthority(r.getCode()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账号是否已过期(未使用)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否已锁定(未使用)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号(未使用)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否启用
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
