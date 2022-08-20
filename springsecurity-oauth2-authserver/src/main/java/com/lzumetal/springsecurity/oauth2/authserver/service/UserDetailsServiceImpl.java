package com.lzumetal.springsecurity.oauth2.authserver.service;

import com.lzumetal.springsecurity.oauth2.authserver.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userFromDb = sysUserService.getByUsername(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException(username);
        }
        return userFromDb;
    }


}
