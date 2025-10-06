package com.app.controller;

import java.util.*;
import com.app.service.HonorkingsService;
import com.app.repository.HonorkingsRepository;
import com.app.model.Honorkings;

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
@RequestMapping(value = "/honorkings")
public class HonorkingsController {

    @Autowired
    HonorkingsService honorkingsService;

    @Autowired
    HonorkingsRepository honorkingsRepository;

    /**
     * 一览表示
     */
    @RequestMapping(value = "search")
    public String doSearch(Model model, Honorkings inDto) {
        List<Honorkings> resultList = honorkingsService.getSearchList(inDto);
        model.addAttribute("resultList", resultList);
        model.addAttribute("inDto", inDto);
        return "honorkings/list";
    }

    /**
     * AJAX检索
     */
    @RequestMapping(value = "ajaxSearch")
    @ResponseBody
    public Object doAjaxSearch(@RequestBody Honorkings honorkings) {
        List<Honorkings> resultList = honorkingsRepository.selectByDto(honorkings);
        return resultList;
    }

    /**
     * 取得单条数据
     */
    @RequestMapping(value = "detail/{id}")
    public String getDetail(@PathVariable("id") String id, Model model) {
        Honorkings honorkings = honorkingsRepository.selectOneById(id);
        model.addAttribute("honorkings",honorkings);
        return "honorkings/detail";
    }

    /**
     * Ajax更新
     */
    @RequestMapping(value = "ajaxRegist", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String doAjaxRegist(@RequestBody Honorkings obj) {
        // 判断是更新还是新规
        String id = obj.getId();

        if (id.length() > 0) {
            honorkingsRepository.update(obj);
        } else {
            honorkingsRepository.insert(obj);
        }

        return "update success!";
    }

}

