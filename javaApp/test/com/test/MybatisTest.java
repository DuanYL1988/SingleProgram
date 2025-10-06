package com.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.application.jdbc.MybatisBase;
import com.application.model.Hero;
import com.application.model.Servant;
import com.application.repository.HeroRepository;
import com.application.repository.ServantRepository;

@RunWith(JUnit4.class)
public class MybatisTest {

    @Test
    public void testMybatisSqlite() {
        MybatisBase conn = new MybatisBase();
        ServantRepository dao = conn.session.getMapper(ServantRepository.class);
        Servant result = dao.selectOneById("S002");
        System.out.println(result.getName());
        conn.colseSession();
    }

    @Test
    public void testAutoCreateDao() {
        MybatisBase conn = new MybatisBase();
        SqlSession session = conn.getSession();

        HeroRepository dao = session.getMapper(HeroRepository.class);
        Hero result = dao.selectOneById(250);
        System.out.println(result.getTitleName() + result.getName());
    }

}
