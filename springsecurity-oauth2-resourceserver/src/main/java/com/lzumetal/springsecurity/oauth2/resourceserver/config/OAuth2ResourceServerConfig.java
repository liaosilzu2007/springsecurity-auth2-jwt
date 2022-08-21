package com.lzumetal.springsecurity.oauth2.resourceserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author liaosi
 * @date 2022-08-21
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        /**
         * 在每个ResourceServer（资源服务器）实例上设置resourceId，该resourceId作为该服务资源的唯一标识。（假如同一个微服务资源部署多份，resourceId相同）。
         *
         * ResourceId是在Resource Server资源服务器进行验证。当资源请求发送到Resource Server的时候会携带access_token，Resource Server会根据access_token找到client_id，进而找到该client可以访问的resource_ids（通过oauth_client_details表）。如果resource_ids包含Resource Server自己设置ResourceId，就可以继续进行其他的权限验证。
         */
        resources.resourceId("resource_a");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()   //关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //基于token，不使用session，所以禁用session机制
                .and()
                //如下是权限管理
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/baidu").access("#oauth2.hasScope('scope_bd')")   //scope权限验证
                .anyRequest().authenticated()
        ;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }



}
