package com.lzumetal.springsecurity.config;

import com.lzumetal.springsecurity.fillter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liaosi
 * @date 2022-08-02
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * @param httpSecurity HttpSecurity类似于命名空间配置中 Spring Security 的 XML 元素。 它允许为特定的 http 请求配置基于 Web 的安全性。
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()   //关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //基于token，不使用session，所以禁用session机制
                .and()
                //如下是权限管理
                .authorizeRequests()
                /* 对于登录接口允许匿名访问。
                   anonymous和permitAll的区别：
                   anonymous() 允许匿名用户访问,不允许已登入用户访问 （Returns true if the current principal is an anonymous user）。
                   permitAll(): 不管已登录或未登录都能访问（Always evaluates to true ）
                 */
                //antMatchers中的参数必须以/开头，**代表多级，*代表一级
                .antMatchers("/**/user/login").anonymous()
                .antMatchers("/**/admin").hasRole("ADMIN")

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()

                //把jwt token校验过滤器添加到过滤器链中，并放在UsernamePasswordAuthenticationFilter之前
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

//                .exceptionHandling()
//                .accessDeniedHandler()
        ;

    }



}
