package com.lzumetal.springsecurity.service;

import com.lzumetal.springsecurity.common.ServiceException;
import com.lzumetal.springsecurity.entity.SysUser;
import com.lzumetal.springsecurity.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author liaosi
 * @date 2022-08-06
 */
@Service
public class LoginService {


    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String userLogin(String username, String password) {
        // 登陆检测
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ServiceException(405, "用户名或密码不正确!");
        }

        //使用userid生成token
        SysUser sysUser = (SysUser) userDetails;
        Integer userId = sysUser.getId();
        if (userId == null) {
            throw new RuntimeException("服务器错误");
        }
        return JWTUtils.generateJwtToken(String.valueOf(userId));
    }


}
