package com.lzumetal.springsecurity.service;

import com.lzumetal.springsecurity.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaosi
 * @date 2022-08-02
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userFromDb = sysUserService.findUser(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException(username);
        }
        return userFromDb;
    }


}
