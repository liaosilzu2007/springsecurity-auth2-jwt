package com.lzumetal.springsecurity.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lzumetal.springsecurity.entity.SysUser;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liaosi
 * @date 2022-08-09
 */
@Service
public class SysUserService {

    private static List<SysUser> usersFromDb = new ArrayList<>();

    /**
     * 模拟用户权限，key为用户id，value为权限集合
     */
    private static Map<Integer, Set<String>> permissions = new HashMap<>();

    static {
        usersFromDb.add(new SysUser(1,
                "zhangsan",
                "$2a$10$2A9boPmN4EQiah93ypuNNuJV1lSb9bMVigsOhAjI8s2yOP0u5r2W2", //密码是123，BCryptPasswordEncoder加密
                "张三"));
        usersFromDb.add(new SysUser(2,
                "lisi",
                "$2a$10$ddbqmCp1WZJlDXVxBdlFH.l2keTK1fGfG5yt4OX8YUDgTF4fs2iGO", //密码是456，BCryptPasswordEncoder加密
                "李四"));
        permissions.put(1, Sets.newHashSet("ROLE_ADMIN", "test", "write"));
        permissions.put(2, Sets.newHashSet("ROLE_USER", "test", "read"));
    }


    /**
     * 模拟根据用户名从数据库中查询数据
     *
     * @param username
     * @return
     */
    public SysUser findUser(String username) {
        for (SysUser sysUser : usersFromDb) {
            if (sysUser.getUsername().equals(username)) {
                return sysUser;
            }
        }
        return null;
    }


    public SysUser findById(Integer userId) {
        for (SysUser sysUser : usersFromDb) {
            if (Objects.equals(sysUser.getId(), userId)) {
                return sysUser;
            }
        }
        return null;
    }


    public Set<String> getUserAuthority(Integer userId) {
        return permissions.get(userId);
    }

}
