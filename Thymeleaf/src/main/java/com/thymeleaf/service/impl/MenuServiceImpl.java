package com.thymeleaf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.model.Menu;
import com.thymeleaf.repository.MenuRepository;
import com.thymeleaf.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository dao;

    /**
     * 根据menu的应用进行分组
     */
    @Override
    public Map<String, List<Menu>> getIndexMenu() {
        Map<String, List<Menu>> menuGroup = new HashMap<String, List<Menu>>();

        Menu condition = new Menu();
        condition.setPageSize(100);

        List<Menu> menuList = dao.selectByDto(condition);

        for (Menu menu : menuList) {
            String application = menu.getApplication();
            List<Menu> childMenu = menuGroup.get(application);
            if (null == childMenu) {
                childMenu = new ArrayList<Menu>();
            }
            childMenu.add(menu);
            menuGroup.put(application, childMenu);
        }
        return menuGroup;
    }

}