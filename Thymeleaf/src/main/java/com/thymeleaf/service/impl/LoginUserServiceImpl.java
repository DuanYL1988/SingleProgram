package com.thymeleaf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thymeleaf.model.Account;
import com.thymeleaf.model.LoginUser;
import com.thymeleaf.repository.AccountRepository;

@Service
public class LoginUserServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * 用户登录
     *
     * @param  username
     * @param  password
     * @param  phone
     * @return
     */
    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户查询
        Account account = null;
        Account condition = new Account();
        try {
            if ("admin".equals(username)) {
                condition.setLoginName(username);
                account = accountRepository.selectByDto(condition).get(0);
            } else {
                // 用户名或手机号登录
                account = accountRepository.selectOneByUniqueKey(username);
            }
            //
            if (Objects.isNull(account)) {
                throw new UsernameNotFoundException("用户不存在");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 权限验证
        List<String> roleList = new ArrayList<>(Arrays.asList(account.getRoleId()));
        // SpringSecurity + JWT用户验证

        return new LoginUser(account, roleList);
    }

}
