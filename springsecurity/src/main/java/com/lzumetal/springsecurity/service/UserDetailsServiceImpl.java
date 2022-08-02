package com.lzumetal.springsecurity.service;

import com.lzumetal.springsecurity.entity.SysUser;
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

    private static List<SysUser> usersFromDb = new ArrayList<>();

    static {
        usersFromDb.add(new SysUser(1,
                "zhangsan",
                "$2a$10$2A9boPmN4EQiah93ypuNNuJV1lSb9bMVigsOhAjI8s2yOP0u5r2W2", //密码是123，BCryptPasswordEncoder加密
                "张三"));
        usersFromDb.add(new SysUser(1,
                "lisi",
                "$2a$10$ddbqmCp1WZJlDXVxBdlFH.l2keTK1fGfG5yt4OX8YUDgTF4fs2iGO", //密码是456，BCryptPasswordEncoder加密
                "李四"));
    }


    /**
     * 模拟根据用户名从数据库中查询数据
     *
     * @param username
     * @return
     */
    private SysUser findUserFromDb(String username) {
        for (SysUser sysUser : usersFromDb) {
            if (sysUser.getUsername().equals(username)) {
                return sysUser;
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userFromDb = findUserFromDb(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException(username);
        }
        return userFromDb;
    }


}
