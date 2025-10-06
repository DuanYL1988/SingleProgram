package com.application.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.application.util.FileUtils;
import com.application.util.TextUtil;

public class GetTblColFromSql {

    public static String CRLF = "\r\n";

    List<String> sqlInfo = new ArrayList<String>();

    Map<String, String> tblNmMp = new HashMap<String, String>();

    Map<String, Set<String>> tblColsMap = new HashMap<String, Set<String>>();

    public static void main(String[] args) throws Exception {
        GetTblColFromSql local = new GetTblColFromSql();
        local.doMain();
    }

    public void doMain() throws Exception {
        sqlInfo = FileUtils.getFileText(new File("D:\\app\\130-8.メール宛先取得.sql"));

        boolean tableFlag = false;
        boolean condFlag = false;
        //
        int lineNo = 0;
        for (String line : sqlInfo) {
            // 表连接部分控制
            if (line.indexOf("FROM ") == 0) {
                tableFlag = true;
            } else if (line.indexOf("WHERE ") == 0) {
                tableFlag = false;
                condFlag = true;
            }

            if (tableFlag) {
                getTblandCol(line, lineNo);
            }

            // where之后部分
            if (condFlag) {
                getColumn(line, lineNo);
            }
            lineNo++;
        }

        // 初始化
        lineNo = 0;
        boolean selectFlag = true;
        for (String line : sqlInfo) {
            // 表连接部分控制
            if (line.indexOf("FROM ") == 0) {
                selectFlag = false;
            }

            if (selectFlag) {
                getColumn(line, lineNo);
            }
            lineNo++;
        }

        Set<String> tblNmList = tblColsMap.keySet();
        StringBuilder dropDDL = new StringBuilder();
        StringBuilder tblDDL = new StringBuilder();
        for (String tblNm : tblNmList) {
            dropDDL.append("DROP TABLE IF EXISTS " + tblNm + ";" + CRLF);
            tblDDL.append("CREATE TABLE " + tblNm + " (" + CRLF);
            // 取得字段
            int index = 0;
            Set<String> tblColumns = tblColsMap.get(tblNm);
            for (String col : tblColsMap.get(tblNm)) {
                String column = index == 0 ? "    " + col : "    , " + col;
                tblDDL.append("    " + column + " varchar(100)" + CRLF);
                index++;
                if (index == tblColumns.size()) {
                    tblDDL.append(");" + CRLF);
                }
            }
        }

        String ddl = dropDDL + CRLF + tblDDL;
        System.out.println(ddl);
    }

    private void getTblandCol(String line, int lineNo) {
        // 取得表名
        if (line.indexOf("FROM") >= 0 || line.indexOf("JOIN") >= 0) {
            String tblLine = "";
            if (line.indexOf("FROM") >= 0) {
                tblLine = line.substring(line.indexOf("FROM") + 5);
            } else {
                tblLine = line.substring(line.indexOf("JOIN") + 5);
            }

            // 取得表名和别称
            String[] tblNm = tblLine.split(" ");
            // 表名
            String dbTblNm = tblNm[0];
            if (tblNm.length > 1) {
                tblNmMp.put(tblNm[1], dbTblNm);
            }

            // 集合初始化
            if (null == tblColsMap.get(dbTblNm)) {
                Set<String> colList = new HashSet<String>();
                tblColsMap.put(dbTblNm, colList);
            }

        }

        // 取得字段名
        if (line.indexOf("ON ") == 0) {
            getColumn(line, lineNo);
        }
    }

    private void getColumn(String line, int lineIndex) {
        // 取得 table.colunm
        List<String> tblColArr = TextUtil.getPatternStr(line, "[a-zA-Z0-9_]*\\.[a-zA-Z0-9_]*");
        // 通常table.colunm写法
        for (String tblCol : tblColArr) {
            // 分开表名和字段名
            String tblNm = tblCol.split("\\.")[0];
            String colNm = tblCol.split("\\.")[1];
            // 直接使用表名
            if (!tblColsMap.containsKey(tblNm)) {
                // 通过别名取表名
                tblNm = tblNmMp.get(tblNm);
                // 添加字段
                Set<String> colList = tblColsMap.get(tblNm);
                if (null != colList) {
                    colList.add(colNm);
                }
            }
        }
        // 不写表别名时
        if (line.indexOf("FROM ") >= 0) {
            // 表名
            String tblNm = line.substring(line.indexOf("FROM") + 5);
            String[] tblNminfo = tblNm.split(" ");
            if (tblNminfo.length == 1) {
                tblNm = tblNminfo[0];
                String preLine = sqlInfo.get(lineIndex - 1).trim();
                preLine = preLine.substring(preLine.indexOf("SELECT") + 6).trim();
                String nextLine = sqlInfo.get(lineIndex + 1).trim();
                nextLine = nextLine.substring(nextLine.indexOf("WHERE") + 6, nextLine.indexOf("=")).trim();
                // 添加字段
                Set<String> colList = tblColsMap.get(tblNm);
                if (null == colList) {
                    colList = new HashSet<String>();
                }
                colList.add(preLine);
                colList.add(nextLine);
                tblColsMap.put(tblNm, colList);
            }
        }
    }
}
