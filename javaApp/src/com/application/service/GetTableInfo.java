package com.application.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.common.Context;
import com.application.dto.Table;
import com.application.dto.Table.Column;
import com.application.util.FileUtils;
import com.application.util.TextUtil;

/**
 * 通过DDL生成Table信息
 *
 * @author dylsw
 *
 */
public class GetTableInfo {

    /**
     * 通过DDL取得table对象
     *
     * @param  fileName    ddl文件名
     * @return
     * @throws IOException
     */
    public Table getTableInfoFromFile(String fileName) throws IOException {
        String path = FileUtils.getPath() + "\\" + fileName;
        List<String> ddlText = FileUtils.getFileText(new File(path));
        return getTable(ddlText);
    }

    /**
     * 取得table内容
     *
     * @param  ddlText
     * @return
     */
    private Table getTable(List<String> ddlText) {
        // 表对象
        Table table = new Table();
        // 表中字段对象
        List<Column> tblColList = new ArrayList<Table.Column>();
        // 逻辑主键字段
        List<Column> uniqueKey = new ArrayList<Table.Column>();

        // 设置
        for (int lineNo = 0; lineNo < ddlText.size(); lineNo++) {
            // 转大写
            String line = ddlText.get(lineNo).toUpperCase();

            // 基本信息[DATABASE.TABLE]
            if (lineNo == 0) {
                // CREATE TABLE IF NOT EXISTS DB.TB (
                String baseInfo = getBaseInfo(line);
                if (baseInfo.indexOf(Context.POINT) > 0) {
                    // DB名
                    table.setDatabaseName(baseInfo.split("\\.")[0]);
                    // 表名
                    table.setName(baseInfo.split("\\.")[1]);
                } else {
                    table.setName(baseInfo);
                }
                continue;
            }

            // SQL结束行
            if (line.indexOf(")") == 0 || line.indexOf(",PRIMARY KEY") == 0 || line.indexOf("PRIMARY KEY") == 0) {
                break;
            }

            // 字段通用属性
            Column col = table.getColumnInstance();
            Map<String, String> colInfoMap = getColumnInfo(line);
            // 字段
            String ddlColumn = colInfoMap.get("column");
            col.setColumnName(ddlColumn);
            col.setSourceName(TextUtil.transNmDbToJava(ddlColumn, false));
            col.setColumnType(colInfoMap.get("type"));
            col.setJavaType(colInfoMap.get("javaType"));
            if (!"".equals(colInfoMap.get("length"))) {
                col.setLength(Integer.parseInt(colInfoMap.get("length")));
            }
            col.setLogicName(colInfoMap.get("logicName"));
            // 主键
            if (line.indexOf("PRIMARY KEY") > 0 || line.indexOf("PRIMARYKEY") > 0) {
                table.setPkey(col);
            }
            // 逻辑主键
            if (line.indexOf("UNIQUE") > 0) {
                uniqueKey.add(col);
            }
            tblColList.add(col);
        }

        table.setColumnList(tblColList);
        table.setUniqueKey(uniqueKey);

        return table;
    }

    /**
     * DB类型转换java类型
     *
     * @param  dbType
     * @param  length
     * @return
     */
    private String javaTypeMap(String dbType) {
        String type = "String";
        if ("INTEGER".equals(dbType) || "INT".equals(dbType)) {
            type = "Integer";
        } else if ("NUMBER".equals(dbType) || "NUMERIC".equals(dbType)) {
            type = "BigDecimal";
        } else if ("TIMESTAMP".equals(dbType) || "DATE".equals(dbType)) {
            type = "Date";
        }
        return type;
    }

    private String dbTypeMap(String dbType) {
        if ("NUMBER".equals(dbType)) {
            dbType = "INTEGER";
        } else if ("VARCHAR2".equals(dbType)) {
            dbType = "VARCHAR";
        }
        return dbType;
    }

    private String getBaseInfo(String firstLine) {
        String baseInfo = firstLine;
        baseInfo = baseInfo.replace("CREATE ", "");
        baseInfo = baseInfo.replace("TABLE ", "");
        baseInfo = baseInfo.replace("IF ", "");
        baseInfo = baseInfo.replace("NOT ", "");
        baseInfo = baseInfo.replace("EXISTS ", "");
        baseInfo = baseInfo.replace("(", "");
        baseInfo = baseInfo.trim();
        return baseInfo;
    }

    private Map<String, String> getColumnInfo(String line) {
        Map<String, String> infoMap = new HashMap<String, String>();
        line = TextUtil.replaceLineHeard(line);
        String[] infoArr = line.split(" ");
        // 字段
        infoMap.put("column", infoArr[0]);
        // 类型,长度
        String type = infoArr[1];
        String javaType = type;
        if (type.indexOf("(") > 0) {
            javaType = type.substring(0, type.indexOf("("));
            infoMap.put("type", dbTypeMap(type.substring(0, type.indexOf("("))));
            infoMap.put("length", type.substring(type.indexOf("(") + 1, type.length() - 1));
        } else {
            infoMap.put("type", dbTypeMap(type));
            infoMap.put("length", "");
        }
        infoMap.put("javaType", javaTypeMap(javaType));
        // 伦理名
        infoMap.put("logicName", "");
        if (line.indexOf("--") > 0) {
            String loginNm = line.substring(line.indexOf("--"));
            // 去除伦理名里的英文
            loginNm = loginNm.replaceAll("[-a-zA-Z]", "").trim();
            infoMap.put("logicName", loginNm);
        } else if (line.indexOf("COMMENT") > 0) {
            String loginNm = line.substring(line.indexOf("COMMENT"));
            loginNm = loginNm.replaceAll("[-a-zA-Z]", "").trim();
            infoMap.put("logicName", loginNm);
        }
        return infoMap;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        String line = " , column varcha(20) primary key";
        GetTableInfo test = new GetTableInfo();
        // Map<String, String> result = test.getColumnInfo(line);
        String ddlFile = "PROJECT.ddl";
        Table table = test.getTableInfoFromFile(ddlFile);
        System.out.println(table.getName());
    }
}
