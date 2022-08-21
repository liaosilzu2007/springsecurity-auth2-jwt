package com.lzumetal.springsecurity.oauth2.authserver.service;

import com.lzumetal.springsecurity.oauth2.authserver.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaosi
 * @date 2022-08-21
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    public List<String> getRoleCode(int userId) {
        return userRoleDao.selectUserRoleCode(userId);
    }

}
