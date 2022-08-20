package com.lzumetal.springsecurity.oauth2.authserver.dao;

import com.lzumetal.springsecurity.oauth2.authserver.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@Mapper
public interface SysUserDao {

    SysUser selectByUsername(@Param("username") String username);

}
