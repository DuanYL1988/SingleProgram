package com.app.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Heroskin;

@Repository
public interface HeroskinRepository {

    Heroskin selectOneById(@Param("id")String id);

    void insert(Heroskin heroskin);

    void update(Heroskin heroskin);

    Heroskin selectOneByUniqueKey();

    List<Heroskin> selectByDto(Heroskin heroskin);

    int getCountByDto(Heroskin heroskin);

    List<Heroskin> customQuary(Heroskin heroskin);

}


