package com.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((request) -> request //
                .antMatchers("/css/**", "/js/**").permitAll() // 静态资源
                .antMatchers("/login", "/").permitAll() // 不需认证的请求
                .antMatchers("/**").permitAll() // 不需认证的请求
                // .antMatchers("/**").hasRole("administrator") // 权限
                .anyRequest().authenticated());

        http.formLogin((form) -> form.loginPage("/login") // 自定义页面
                .usernameParameter("userName") // 用户名
                .passwordParameter("password") // 密码
                .loginProcessingUrl("/homepage") // 登录请求路径
                .successForwardUrl("/homepage") //
                .permitAll());// (form) -> form.loginPage("/login").permitAll()

        http.logout();

        // 记住我
        // http.rememberMe();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置密码加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 加密方式
     */
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String padInDb = "$2a$10$quUgK2MDWcR/BcWQIRwjZubyVFDf3s04GCVpZMJa8pOKy4OAMLa4C";
        // 校验
        System.out.println(encoder.matches("1", padInDb));

    }

}
