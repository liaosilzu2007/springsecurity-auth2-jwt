package com.lzumetal.springsecurity.oauth2.authserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liaosi
 * @date 2022-08-21
 */
@Mapper
public interface UserRoleDao {

    List<String> selectUserRoleCode(@Param("userId") int userId);

}
