package com.app.controller;

import java.util.*;
import com.app.service.HeroskinService;
import com.app.repository.HeroskinRepository;
import com.app.model.Heroskin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/heroskin")
public class HeroskinController {

    @Autowired
    HeroskinService heroskinService;

    @Autowired
    HeroskinRepository heroskinRepository;

    /**
     * 一览表示
     */
    @RequestMapping(value = "search")
    public String doSearch(Model model, Heroskin inDto) {
        List<Heroskin> resultList = heroskinService.getSearchList(inDto);
        model.addAttribute("resultList", resultList);
        model.addAttribute("inDto", inDto);
        return "heroskin/list";
    }

    /**
     * AJAX检索
     */
    @RequestMapping(value = "ajaxSearch")
    @ResponseBody
    public Object doAjaxSearch(@RequestBody Heroskin heroskin) {
        List<Heroskin> resultList = heroskinRepository.selectByDto(heroskin);
        return resultList;
    }

    /**
     * 取得单条数据
     */
    @RequestMapping(value = "detail/{id}")
    public String getDetail(@PathVariable("id") String id, Model model) {
        Heroskin heroskin = heroskinRepository.selectOneById(id);
        model.addAttribute("heroskin",heroskin);
        return "heroskin/detail";
    }

    /**
     * Ajax更新
     */
    @RequestMapping(value = "ajaxRegist", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String doAjaxRegist(@RequestBody Heroskin obj) {
        // 判断是更新还是新规
        String id = obj.getId();

        if (id.length() > 0) {
            heroskinRepository.update(obj);
        } else {
            heroskinRepository.insert(obj);
        }

        return "update success!";
    }

}

