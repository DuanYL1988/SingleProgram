package com.thymeleaf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thymeleaf.model.Account;
import com.thymeleaf.model.Menu;
import com.thymeleaf.service.MenuService;
import com.thymeleaf.service.impl.LoginUserServiceImpl;

@Controller
public class IndexController {

    @Autowired
    MenuService menuService;

    @Autowired
    LoginUserServiceImpl security;

    @GetMapping({ "/login", "/" })
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("applicationName", "Thymeleaf Project");
        session.setAttribute("footerLabel", "Duan Yanglong Cn[1988]");
        request.setAttribute("pageTitle", "Login");
        return "login";
    }

    /**
     * @param  model
     * @param  condition
     * @return
     */
    @RequestMapping("/homepage")
    public String thymeleafIndex(Account account, Model model, HttpServletRequest request) {
        pageInit(model);
        HttpSession session = request.getSession();
        Map<String, List<Menu>> menuList = menuService.getIndexMenu();
        session.setAttribute("menuList", menuList);
        model.addAttribute("replaceContent", "fe/list :: content");
        return "index";
    }

    @GetMapping("showDetail")
    public String showDetail(@Param("parent") String parent, @Param("app") String app, @Param("arg") String arg, Model model) {
        System.out.println(parent + ";" + app + ";" + arg);
        pageInit(model);
        return "index";
    }

    private void pageInit(Model model) {
        model.addAttribute("message", "hello thymeleaf!!!");
    }
}
