package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.application.common.Context;
import com.application.creater.DaoCreater;
import com.application.creater.ModelCreater;
import com.application.dto.Table;
import com.application.service.GetTableInfo;

@RunWith(JUnit4.class)
public class CreateResouceTest {
    @Test
    public void testReadDDL() throws IOException {
        Map<String, String> configMap = new HashMap<String, String>();
        configMap.put(Context.SPRINGBOOT_FLAG, "N");
        configMap.put(Context.ORACLE, "N");
        //
        boolean hibernateFlag = false;
        // String projectName = "SpringBootProject";
        // String basePkg = "com.springboot.demo";
        String projectName = "javaApp";
        String basePkg = "com.application";
        boolean createFlag = true;
        //
        GetTableInfo service = new GetTableInfo();
        // 生成对象
        // String[] targetArr = new String[] { "SERVANT", "HERO" };
        String[] targetArr = new String[] { "HONORKINGS" };

        for (String target : targetArr) {
            String resourceDDL = "resources\\" + target + ".ddl";
            Table table = service.getTableInfoFromFile(resourceDDL);
            // 控制层Controller
            // PageCreater page = new PageCreater();
            // page.createController(table, projectName, basePkg, createFlag, hibernateFlag);
            // page.createHtml(table, "SpringBootResource", "templates\\", "list", createFlag);
            // page.createHtml(table, "SpringBootResource", "templates\\", "detail", createFlag);

            // // 业务层Service
            // ServiceCreater serviceC = new ServiceCreater();
            // serviceC.createServiceImpl(table, projectName, basePkg, createFlag, hibernateFlag);
            // serviceC.createService(table, projectName, basePkg, createFlag);

            // // 持久化层Dao
            DaoCreater daoC = new DaoCreater();
            // daoC.createMybatis(table, projectName, basePkg, createFlag);
            daoC.createMappingXml(table, "MybatisXml", basePkg, configMap, false);
            if (hibernateFlag) {
                daoC.createHibernate(table, projectName, basePkg, createFlag);
            }

            // // DB对象Model
            ModelCreater creater = new ModelCreater();
            creater.createModel(table, projectName, basePkg, createFlag, hibernateFlag);
            // System.out.println(result);
        }
    }

}
