package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Servant;
import com.app.repository.ServantRepository;
import com.app.service.ServantService;

@Service
public class ServantServiceImpl implements ServantService {

    @Autowired
    ServantRepository servantRepository;

    @Override
    public List<Servant> getSearchList(Servant inDto) {
        List<Servant> resultList = new ArrayList<Servant>();

        inDto = null == inDto ? new Servant() : inDto;
        int recCnt = servantRepository.getCountByDto(inDto);
        int maxPage = recCnt % inDto.getPageSize() > 0 ? recCnt / inDto.getPageSize() + 1 : recCnt / inDto.getPageSize();
        inDto.setMaxPage(maxPage);
        inDto.setStartRow((inDto.getPageNo() - 1) * inDto.getPageSize());
        resultList = servantRepository.selectByDto(inDto);
        return resultList;
    }

    @Override
    public Servant selectOneById(Integer id) {
        Servant result = new Servant();

        result = servantRepository.selectOneById(id + "");
        return result;
    }

    @Override
    public int doInsertOrUpdate(Servant inDto) {
        int recCnt = -1;
        if (null == inDto.getId()) {
            recCnt = servantRepository.insert(inDto);
        } else {
            recCnt = servantRepository.update(inDto);
        }
        return recCnt;
    }

    @Override
    public void delete(Integer id) {
        servantRepository.delete(id + "");
    }

}
