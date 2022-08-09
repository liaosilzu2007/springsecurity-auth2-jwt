package com.lzumetal.springsecurity.fillter;

import com.lzumetal.springsecurity.common.ServiceException;
import com.lzumetal.springsecurity.entity.SysUser;
import com.lzumetal.springsecurity.service.SysUserService;
import com.lzumetal.springsecurity.util.JWTUtils;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            throw new ServiceException(403, "Forbidden");
        }

        //解析token
        String userId = JWTUtils.getUserIdByJwtToken(token);
        if (userId == null) {
            throw new ServiceException(403, "Forbidden");
        }

        /*
         * 查出userId对应的UserDetails实例以及对应的权限，可以从数据库查，也可以从redis中查（可以在登录时保存到redis中）
         * 不建议直接将权限保存的token里
         */
        SysUser sysUser = sysUserService.findById(Integer.parseInt(userId));
        Set<String> userAuthority = sysUserService.getUserAuthority(Integer.parseInt(userId));
        sysUser.buildAuthorities(userAuthority);


        //获取用户、权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser,null,sysUser.getAuthorities());

        //存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);

    }

}
