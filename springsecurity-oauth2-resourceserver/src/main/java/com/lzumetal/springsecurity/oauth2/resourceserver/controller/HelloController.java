package com.lzumetal.springsecurity.oauth2.resourceserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2022-08-21
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("test-auth")
    public String testAuth() {
        return "request success";
    }


    @RequestMapping("/admin")
    public String admin() {
        return "hello admin";
    }


    @RequestMapping("/baidu")
    public String baidu() {
        return "hello baidu";
    }
}
