package com.thymeleaf;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.thymeleaf.model.Account;
import com.thymeleaf.util.JwtUtil;

public class JwtTester extends BaseTester {

    @Test
    public void testJwt() {
        Account account = new Account();
        String userName = "alimiam";
        String role = "fireemblem";
        account.setUsername(userName);
        account.setApplication(role);
        // 取得token
        String token = JwtUtil.createJwt(account);
        System.out.println(token);

        // 验证token
        Map<String, String> info = JwtUtil.parseToken(token);

        Assert.assertEquals(info.get("name"), userName);
        Assert.assertEquals(info.get("role"), role);
    }

    @Test
    public void testPsdEncoder() {
        String psd = "13329294560";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String psdEn = encoder.encode(psd);
        System.out.println(psdEn);
        Assert.assertTrue(encoder.matches(psd, psdEn));
    }
}
