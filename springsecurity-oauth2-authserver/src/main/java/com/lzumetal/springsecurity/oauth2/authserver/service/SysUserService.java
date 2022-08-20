package com.lzumetal.springsecurity.oauth2.authserver.service;

import com.lzumetal.springsecurity.oauth2.authserver.dao.SysUserDao;
import com.lzumetal.springsecurity.oauth2.authserver.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;



    public SysUser getByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return sysUserDao.selectByUsername(username);
    }


}
