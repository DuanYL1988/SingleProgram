package com.thymeleaf.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thymeleaf.model.Account;
import com.thymeleaf.repository.AccountRepository;
import com.thymeleaf.util.JwtUtil;

@RestController
public class RestVueController {

    @Autowired
    AccountRepository dao;

    /**
     * @param  model
     * @param  condition
     * @return
     */
    @GetMapping(value = "vue/login")
    public Account thymeleafIndex(@Param("userName") String userName, @Param("password") String password) {
        // 检索用户
        Account loginUser = dao.selectOneById(1);
        String token = JwtUtil.createJwt(loginUser);
        loginUser.setToken(token);
        return loginUser;
    }

}
