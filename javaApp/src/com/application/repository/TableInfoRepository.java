package com.application.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.application.model.TableInfo;

public interface TableInfoRepository {

    TableInfo selectOneById(@Param("id") Integer id);

    TableInfo selectOneByUniqueKey(@Param("tableName") String tableName, @Param("colName") String colName);

    int getCountByDto(TableInfo tableinfo);

    List<TableInfo> selectByDto(TableInfo tableinfo);

    int insert(TableInfo tableinfo);

    int update(TableInfo tableinfo);

    int delete(@Param("id") Integer id);

    List<TableInfo> customQuary(TableInfo tableinfo);

}
