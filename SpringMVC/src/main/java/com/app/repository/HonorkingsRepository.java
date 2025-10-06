package com.app.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Honorkings;

@Repository
public interface HonorkingsRepository {

    Honorkings selectOneById(@Param("id")String id);

    void insert(Honorkings honorkings);

    void update(Honorkings honorkings);

    Honorkings selectOneByUniqueKey();

    List<Honorkings> selectByDto(Honorkings honorkings);

    int getCountByDto(Honorkings honorkings);

    List<Honorkings> customQuary(Honorkings honorkings);

}


