package com.thymeleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thymeleaf.model.TableColumn;

@Repository
public interface TableColumnRepository {

    List<TableColumn> selectByDto(TableColumn tableColumn);

}
