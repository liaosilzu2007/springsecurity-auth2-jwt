package com.lzumetal.springsecurity.jwt.fillter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springsecurity.core.ResponseData;
import com.lzumetal.springsecurity.jwt.entity.SysUser;
import com.lzumetal.springsecurity.jwt.service.SysUserService;
import com.lzumetal.springsecurity.jwt.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author liaosi
 * @date 2022-08-09
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录接口，则放行
        String requestURI = request.getRequestURI();
        if ("/springsecurity/user/login".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        response.setContentType("application/json;charset=utf-8");
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            ResponseData responseData = new ResponseData(403, "Forbidden");
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
            return;
        }

        //解析token
        String userId = JWTUtils.getUserIdByJwtToken(token);
        if (userId == null) {
            ResponseData responseData = new ResponseData(403, "Forbidden");
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
            return;
        }

        /*
         * 查出userId对应的UserDetails实例以及对应的权限，可以从数据库查，也可以从redis中查（可以在登录时保存到redis中）
         * 也可以将权限直接保存的token里，因为认证服务一般和业务服务是分开的，业务中不会在去查用户的权限。
         * 或者如果有一个网关的话，在网关中再去查询权限也可以。
         */
        SysUser sysUser = sysUserService.findById(Integer.parseInt(userId));
        Set<String> userAuthority = sysUserService.getUserAuthority(Integer.parseInt(userId));
        sysUser.buildAuthorities(userAuthority);

        //获取用户、权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getAuthorities());

        //存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);

    }

}
