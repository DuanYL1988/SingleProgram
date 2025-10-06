package com.app.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Master;

@Repository
public interface MasterRepository {

    Master selectOneById(@Param("id")Integer id);

    Master selectOneByUniqueKey();

    int getCountByDto(Master master);

    List<Master> selectByDto(Master master);

    int insert(Master master);

    int update(Master master);

    int delete(@Param("id")Integer id);

    List<Master> customQuary(Master master);

}


