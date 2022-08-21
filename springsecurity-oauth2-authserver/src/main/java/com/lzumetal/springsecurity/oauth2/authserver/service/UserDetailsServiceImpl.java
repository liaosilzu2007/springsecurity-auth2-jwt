package com.lzumetal.springsecurity.oauth2.authserver.service;

import com.lzumetal.springsecurity.oauth2.authserver.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userFromDb = sysUserService.getByUsername(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException(username);
        }
        List<String> roleCodes = userRoleService.getRoleCode(userFromDb.getId());
        if (!CollectionUtils.isEmpty(roleCodes)) {
            HashSet<String> roleAuths = new HashSet<>();
            for (String roleCode : roleCodes) {
                roleAuths.add("ROLE_" + roleCode);
            }
            userFromDb.buildAuthorities(roleAuths);
        }
        return userFromDb;
    }



}
