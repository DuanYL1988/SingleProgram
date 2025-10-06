package com.app.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.service.HonorkingsService;
import com.app.repository.HonorkingsRepository;
import com.app.model.Honorkings;

@Service
public class HonorkingsServiceImpl implements HonorkingsService {

    @Autowired
    HonorkingsRepository honorkingsRepository;

    public List<Honorkings> getSearchList(Honorkings inDto) {
        List<Honorkings> resultList = new ArrayList<Honorkings>();

        inDto = null == inDto ? new Honorkings() : inDto;
        int recCnt = honorkingsRepository.getCountByDto(inDto);
        int maxPage = recCnt % inDto.getPageSize() > 0 ? recCnt / inDto.getPageSize() + 1
                : recCnt / inDto.getPageSize();
        inDto.setMaxPage(maxPage);
        inDto.setStartRow((inDto.getPageNo() - 1) * inDto.getPageSize());
        resultList = honorkingsRepository.selectByDto(inDto);
        return resultList;
    }

}

