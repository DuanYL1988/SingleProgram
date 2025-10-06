package com.application.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.application.model.Servant;

public interface ServantRepository {

    Servant selectOneById(@Param("id") String id);

    Servant selectOneByUniqueKey(@Param("id") String id, @Param("imgName") String imgName, @Param("faceImgUrl") String faceImgUrl, @Param("stageOneImgUrl") String stageOneImgUrl,
            @Param("stageTwoImgUrl") String stageTwoImgUrl, @Param("stageThreeImgUrl") String stageThreeImgUrl, @Param("stageFinalImgUrl") String stageFinalImgUrl, @Param("name") String name,
            @Param("nickName") String nickName, @Param("classType") String classType, @Param("heroType") String heroType, @Param("eventFlag") String eventFlag, @Param("hp") String hp,
            @Param("attact") String attact, @Param("skill1") String skill1);

    int getCountByDto(Servant servant);

    List<Servant> selectByDto(Servant servant);

    int insert(Servant servant);

    int update(Servant servant);

    int delete(@Param("id") String id);

    List<Servant> customQuary(Servant servant);

}
