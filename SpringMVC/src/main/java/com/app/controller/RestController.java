package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.form.HeroSearchForm;
import com.app.model.Hero;
import com.app.model.Honorkings;
import com.app.model.Servant;
import com.app.repository.HonorkingsRepository;
import com.app.repository.ServantRepository;
import com.app.service.HeroService;
import com.app.service.HonorkingsService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping(value = "/restful/")
public class RestController {

    @Autowired
    HeroService heroService;

    @Autowired
    HonorkingsService hkService;

    @Autowired
    HonorkingsRepository repository;

    @Autowired
    ServantRepository servantDao;

    @RequestMapping(value = "/feHeroList")
    public Object getList() {
        HeroSearchForm form = new HeroSearchForm();
        Hero condition = new Hero();
        condition.setName("琳迪斯");

        form.setHero(condition);
        form.setMode("search");
        form.setGroup("A");
        List<Hero> result = heroService.doSearch(form);
        return result;
    }

    @RequestMapping(value = "/honorList")
    public Object getHonorList() {
        List<Honorkings> result = hkService.getSearchList(new Honorkings());
        return result;
    }

    @RequestMapping(value = "/honor/{id}")
    public Object getHonorDetail(@PathVariable("id") String id) {
        Honorkings obj = repository.selectOneById(id);
        return obj;
    }

    @RequestMapping(value = "/servant/{id}")
    public Object getServantDeatil(@PathVariable("id") String id) {
        Servant obj = servantDao.selectOneById(id);
        return obj;
    }
}
