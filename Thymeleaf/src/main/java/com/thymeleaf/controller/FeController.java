package com.thymeleaf.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import com.thymeleaf.model.FireemblemHero;
import com.thymeleaf.model.TableColumn;
import com.thymeleaf.repository.FireemblemHeroRepository;
import com.thymeleaf.repository.TableColumnRepository;

@Controller
public class FeController {

    private final String IMG_HOST = "https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/";

    @Autowired
    FireemblemHeroRepository feDao;

    @Autowired
    TableColumnRepository columnDao;

    @GetMapping("/fe/regist/init")
    public String doInit(Model model) {
        FireemblemHero hero = feDao.selectOneById(1740);
        setInitPage(model, hero);
        return "fe/index";
    }

    @PostMapping("/fe/regist/regist")
    public String doLogin(Model model, @Validated FireemblemHero fireemblemHero, BindingResult bindingResult) {
        System.out.println("Debug Object");
        if (bindingResult.hasErrors()) {
            // 确认错误消息
            System.out.println("has error: {}" + bindingResult.getFieldError());
            System.out.println(" - {}" + bindingResult.getFieldError("titleName"));
        }
        setInitPage(model, fireemblemHero);
        return "fe/index";
    }

    @GetMapping("/fe/list/init")
    public String doSearch(@Param("menu") String menu, Model model) {
        String returnPage = "fe/list";
        if (!StringUtils.isEmpty(menu)) {
            model.addAttribute("replaceContent", "fe/list :: content");
            returnPage = menu;
        }
        //
        TableColumn condition = new TableColumn();
        condition.setTableName("FIREEMBLEM_HERO");
        condition.setListVisable("●");
        condition.setOrderBy(" input_type ");
        List<TableColumn> result = columnDao.selectByDto(condition);

        //
        FireemblemHero cond = new FireemblemHero();
        cond.setOrderBy(" release_date desc ");
        List<FireemblemHero> heroList = feDao.customQuary(cond);

        model.addAttribute("titleList", result);
        model.addAttribute("searchList", heroList);
        model.addAttribute("imgHost", IMG_HOST);

        return returnPage;
    }

    private void setInitPage(Model model, FireemblemHero hero) {
        model.addAttribute("fireemblemHero", hero);
    }
}
