package com.thymeleaf.service;

import java.util.List;
import java.util.Map;

import com.thymeleaf.model.Menu;

public interface MenuService {

    /**
     * 根据menu的应用进行分组
     *
     * @return Map<String, List<Menu>>
     */
    public Map<String, List<Menu>> getIndexMenu();

}