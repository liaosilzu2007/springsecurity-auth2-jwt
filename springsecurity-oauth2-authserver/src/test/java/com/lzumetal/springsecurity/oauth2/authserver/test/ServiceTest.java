package com.lzumetal.springsecurity.oauth2.authserver.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springsecurity.oauth2.authserver.OAuth2AuthServerBootstrap;
import com.lzumetal.springsecurity.oauth2.authserver.dao.SysUserDao;
import com.lzumetal.springsecurity.oauth2.authserver.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2022-08-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OAuth2AuthServerBootstrap.class})
@Slf4j
public class ServiceTest {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testDao() throws JsonProcessingException {
        SysUser user = sysUserDao.selectByUsername("zhangsan");
        log.info("user:{}", objectMapper.writeValueAsString(user));
    }

}
