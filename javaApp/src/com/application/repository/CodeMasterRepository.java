package com.application.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.application.model.CodeMaster;

public interface CodeMasterRepository {

    CodeMaster selectOneById(@Param("id") Integer id);

    CodeMaster selectOneByUniqueKey(@Param("application") String application, @Param("categoryId") String categoryId, @Param("code") String code);

    int getCountByDto(CodeMaster codemaster);

    List<CodeMaster> selectByDto(CodeMaster codemaster);

    int insert(CodeMaster codemaster);

    int update(CodeMaster codemaster);

    int delete(@Param("id") Integer id);

    List<CodeMaster> customQuary(CodeMaster codemaster);

}
