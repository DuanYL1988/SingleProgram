package com.application.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.application.model.Hero;

public interface HeroRepository {

    Hero selectOneById(@Param("id") Integer id);

    Hero selectOneByUniqueKey(@Param("titleName") String titleName, @Param("name") String name);

    int getCountByDto(Hero hero);

    List<Hero> selectByDto(Hero hero);

    int insert(Hero hero);

    int update(Hero hero);

    int delete(@Param("id") Integer id);

    List<Hero> customQuary(Hero hero);

}
