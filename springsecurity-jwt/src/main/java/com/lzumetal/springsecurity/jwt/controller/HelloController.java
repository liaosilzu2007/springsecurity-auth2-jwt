package com.lzumetal.springsecurity.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2022-08-01
 */
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/admin")
    public String admin() {
        return "hello admin";
    }

}
