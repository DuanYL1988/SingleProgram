package com.application.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.application.model.Menu;

public interface MenuRepository {

    Menu selectOneById(@Param("id") Integer id);

    Menu selectOneByUniqueKey(@Param("id") Integer id);

    int getCountByDto(Menu menu);

    List<Menu> selectByDto(Menu menu);

    int insert(Menu menu);

    int update(Menu menu);

    int delete(@Param("id") Integer id);

    List<Menu> customQuary(Menu menu);

}
