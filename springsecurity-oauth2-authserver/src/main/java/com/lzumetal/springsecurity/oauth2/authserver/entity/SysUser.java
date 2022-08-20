package com.lzumetal.springsecurity.oauth2.authserver.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@Getter
@Setter
public class SysUser implements UserDetails {


    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Date createTime;

    private Set<GrantedAuthority> authorities;


    public SysUser() {
    }

    public SysUser(Integer id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }


    /**
     * 设置用户的权限
     *
     * @param set
     */
    public void buildAuthorities(Set<String> set) {
        if (set != null) {
            authorities = new HashSet<>();
            for (String s : set) {
                authorities.add(new SimpleGrantedAuthority(s));
            }
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
