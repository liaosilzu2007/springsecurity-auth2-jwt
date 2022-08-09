package com.lzumetal.springsecurity.controller;

import com.lzumetal.springsecurity.common.ResponseData;
import com.lzumetal.springsecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2022-08-06
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/user/login")
    public ResponseData userLogin(@RequestParam String username,
                                  @RequestParam String password) {
        String jwtToken = loginService.userLogin(username, password);
        return ResponseData.data(jwtToken);
    }


}
