package com.application.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.application.common.Context;

public class MybatisBase {

    public SqlSession session = null;

    public MybatisBase() {
        session = getSession();
    }

    public SqlSession getSession() {
        String dbType = Context.getProp(Context.DB_TYPE);
        String xmlName = "mybatis-config_{dyType}.xml";
        xmlName = xmlName.replace("{dyType}", dbType);
        String resource = xmlName;
        InputStream ins;
        try {
            ins = Resources.getResourceAsStream(resource);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(ins);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    public void colseSession() {
        this.session.close();
    }
}
