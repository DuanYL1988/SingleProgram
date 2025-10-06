package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Servant;
import com.app.service.ServantService;

@Controller
@CrossOrigin
@RequestMapping(value = "/servant")
public class ServantController {

    @Autowired
    ServantService servantService;

    /**
     * 一览表示
     */
    @RequestMapping(value = "search")
    public String doSearch(Model model, Servant inDto) {
        List<Servant> resultList = servantService.getSearchList(inDto);
        model.addAttribute("resultList", resultList);
        model.addAttribute("inDto", inDto);
        return "servant/list";
    }

    /**
     * AJAX检索
     */
    @RequestMapping(value = "ajaxSearch")
    @ResponseBody
    public Object doAjaxSearch(@RequestBody Servant servant) {
        List<Servant> resultList = servantService.getSearchList(servant);
        return resultList;
    }

    /**
     * 取得单条数据
     */
    @RequestMapping(value = "detail/{id}")
    public String getDetail(@PathVariable("id") String id, Model model) {
        Servant servant = servantService.selectOneById(Integer.parseInt(id));
        model.addAttribute("servant", servant);
        return "servant/detail";
    }

    /**
     * Ajax更新
     */
    @RequestMapping(value = "ajaxRegist", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String doAjaxRegist(@RequestBody Servant obj) {
        servantService.doInsertOrUpdate(obj);
        return "update success!";
    }

}
