package com.app.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.service.HeroskinService;
import com.app.repository.HeroskinRepository;
import com.app.model.Heroskin;

@Service
public class HeroskinServiceImpl implements HeroskinService {

    @Autowired
    HeroskinRepository heroskinRepository;

    public List<Heroskin> getSearchList(Heroskin inDto) {
        List<Heroskin> resultList = new ArrayList<Heroskin>();

        inDto = null == inDto ? new Heroskin() : inDto;
        int recCnt = heroskinRepository.getCountByDto(inDto);
        int maxPage = recCnt % inDto.getPageSize() > 0 ? recCnt / inDto.getPageSize() + 1
                : recCnt / inDto.getPageSize();
        inDto.setMaxPage(maxPage);
        inDto.setStartRow((inDto.getPageNo() - 1) * inDto.getPageSize());
        resultList = heroskinRepository.selectByDto(inDto);
        return resultList;
    }

}

