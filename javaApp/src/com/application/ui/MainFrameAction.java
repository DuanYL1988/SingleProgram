package com.application.ui;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JTextArea;

import com.application.common.Context;
import com.application.creater.DaoCreater;
import com.application.creater.ModelCreater;
import com.application.creater.PageCreater;
import com.application.creater.ServiceCreater;
import com.application.dto.Table;
import com.application.service.GetTableInfo;

public class MainFrameAction {

    private static boolean OUTPUT_FLAG = true;

    // 控制层Controller
    PageCreater page = new PageCreater();
    // 业务层Service
    ServiceCreater serviceC = new ServiceCreater();
    // 持久化层Dao
    DaoCreater daoC = new DaoCreater();
    // DB对象Model
    ModelCreater modelC = new ModelCreater();

    public void doMainFrameBtn3(ActionEvent arg) {
    }

    public void doMainFrameCreateDDL(JTextArea result, Map<String, String> resultMap, String[] outPutList, String localPath) {

        try {

            GetTableInfo service = new GetTableInfo();
            // 页面输入的参数
            String targetProj = resultMap.get(Context.PROJECT);
            String targetPkg = resultMap.get(Context.PACKAGE);
            String targetLocation = resultMap.get(Context.LOCATION);

            String htmlPath = "";
            // 循环生成
            for (String ddlFile : outPutList) {
                consoleLog(result, "读取文件,取得表情报 : " + ddlFile);
                Table table = service.getTableInfoFromFile(ddlFile);
                consoleLog(result, table.getName());

                /* 判断是否生成各种resorces */
                if ("Y".equals(resultMap.get(Context.CONTROLLER))) {
                    // Controller
                    createController(result, table, resultMap, targetLocation, targetProj, targetPkg);
                }
                if ("Y".equals(resultMap.get(Context.SERVICE))) {
                    // Service
                    createService(result, table, resultMap, targetLocation, targetPkg);
                }
                if ("Y".equals(resultMap.get(Context.DAO))) {
                    // Dao
                    createDao(result, table, resultMap, targetLocation, targetPkg);
                }
                if ("Y".equals(resultMap.get(Context.HTML))) {
                    createPage(result, table, resultMap, targetLocation, htmlPath);
                }
            }
        } catch (IOException e) {
            consoleLog(result, "该路径文件不存在");
        }

    }

    /**
     * 输出Controller文件
     *
     * @param result
     * @param table
     * @param targetLocation
     * @param targetProj
     * @param targetPkg
     * @param hibernateFlag
     */
    private void createController(JTextArea result, Table table, Map<String, String> resultMap, String targetLocation, String targetProj, String targetPkg) {
        consoleLog(result, "開始輸出文件 : ");
        String fileNm = page.createController(table, targetLocation, targetProj, targetPkg, resultMap, OUTPUT_FLAG);
        consoleLog(result, "輸出文件 : " + fileNm);
    }

    /**
     * 输出页面文件
     *
     * @param  result
     * @param  table
     * @param  targetLocation
     * @param  pageType
     * @param  hibernateFlag
     * @throws IOException
     */
    private void createPage(JTextArea result, Table table, Map<String, String> resultMap, String targetLocation, String pagePath) throws IOException {
        boolean jspFlag = "Y".equals(resultMap.get(Context.JSP_FLAG));
        consoleLog(result, "開始輸出文件 : ");
        String fileNm = page.createHtml(table, targetLocation, pagePath, "list", jspFlag, OUTPUT_FLAG);
        consoleLog(result, "輸出文件 : " + fileNm);
        consoleLog(result, "開始輸出文件 : ");
        fileNm = page.createHtml(table, targetLocation, pagePath, "detail", jspFlag, OUTPUT_FLAG);
        consoleLog(result, "輸出文件 : " + fileNm);
    }

    /**
     * 输出Service
     *
     * @param result
     * @param table
     * @param targetLocation
     * @param targetPkg
     * @param hibernateFlag
     */
    private void createService(JTextArea result, Table table, Map<String, String> resultMap, String targetLocation, String targetPkg) {
        boolean hibernateFlag = "Y".equals(resultMap.get(Context.JPA));
        consoleLog(result, "開始輸出文件 : ");
        String fileNm = serviceC.createServiceImpl(table, targetLocation, targetPkg, OUTPUT_FLAG, hibernateFlag);
        consoleLog(result, "輸出文件 : " + fileNm);

        consoleLog(result, "開始輸出文件 : ");
        fileNm = serviceC.createService(table, targetLocation, targetPkg, OUTPUT_FLAG);
        consoleLog(result, "輸出文件 : " + fileNm);
    }

    /**
     * @param result
     * @param table
     * @param targetLocation
     * @param targetPkg
     * @param hibernateFlag
     */
    private void createDao(JTextArea result, Table table, Map<String, String> resultMap, String targetLocation, String targetPkg) {
        boolean hibernateFlag = "Y".equals(resultMap.get(Context.JPA));
        boolean mybatisFlag = "Y".equals(resultMap.get(Context.MYBATIS));

        String fileNm = "";
        consoleLog(result, "開始輸出文件 : ");
        fileNm = modelC.createModel(table, targetLocation, targetPkg, OUTPUT_FLAG, hibernateFlag);
        consoleLog(result, "輸出文件 : " + fileNm);

        if (hibernateFlag) {
            consoleLog(result, "開始輸出文件 : ");
            fileNm = daoC.createHibernate(table, targetLocation, targetPkg, OUTPUT_FLAG);
            consoleLog(result, "輸出文件 : " + fileNm);
        }

        if (mybatisFlag) {
            consoleLog(result, "開始輸出文件 : ");
            fileNm = daoC.createMybatis(table, targetLocation, targetPkg, resultMap, OUTPUT_FLAG);
            consoleLog(result, "輸出文件 : " + fileNm);

            consoleLog(result, "開始輸出文件 : ");
            fileNm = daoC.createMappingXml(table, targetLocation, targetPkg, resultMap, OUTPUT_FLAG);
            consoleLog(result, "輸出文件 : " + fileNm);
        }

    }

    /**
     * 打印log
     *
     * @param console
     * @param str
     */
    private void consoleLog(JTextArea console, String str) {
        console.setText(console.getText() + str + Context.CRLF);
    }
}
