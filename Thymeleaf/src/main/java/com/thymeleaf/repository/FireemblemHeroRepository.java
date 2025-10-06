package com.thymeleaf.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.thymeleaf.model.FireemblemHero;

@Repository
public interface FireemblemHeroRepository {

    FireemblemHero selectOneById(@Param("id")Integer id);

    FireemblemHero selectOneByUniqueKey(@Param("id")Integer id);

    int getCountByDto(FireemblemHero fireemblemhero);

    List<FireemblemHero> selectByDto(FireemblemHero fireemblemhero);

    int insert(FireemblemHero fireemblemhero);

    int update(FireemblemHero fireemblemhero);

    int delete(@Param("id")Integer id);

    List<FireemblemHero> customQuary(FireemblemHero fireemblemhero);

}


