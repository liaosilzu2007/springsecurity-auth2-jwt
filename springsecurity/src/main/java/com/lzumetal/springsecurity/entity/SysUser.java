package com.lzumetal.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author liaosi
 * @date 2022-08-02
 */
@Getter
@Setter
public class SysUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private String nickname;


    public SysUser() {
    }

    public SysUser(Integer id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    /**
     * 用户是否是非过期状态
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 用户是否是非锁定状态
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * 用户密码是否是非过期状态
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否是激活状态
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}
