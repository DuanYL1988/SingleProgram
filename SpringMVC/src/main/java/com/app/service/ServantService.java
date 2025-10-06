package com.app.service;

import java.util.*;
import com.app.model.Servant;

public interface ServantService {

    List<Servant> getSearchList(Servant servant);

    Servant selectOneById(Integer id);

    int doInsertOrUpdate(Servant servant);

    void delete(Integer id);

}

