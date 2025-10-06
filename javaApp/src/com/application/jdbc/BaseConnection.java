package com.application.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseConnection {

    /**
     * 控制台输出检索结果
     *
     * @param recodeList
     */
    public void displayResult(List<Map<String, String>> recodeList) {
        System.out.println("[");
        for (Map<String, String> recoder : recodeList) {
            Set<String> columns = recoder.keySet();
            System.out.print("{");
            for (String column : columns) {
                System.out.print("'" + formatKeyname(column) + "' : '" + recoder.get(column) + "' , ");
            }
            System.out.print("},\n");
        }
        System.out.println("]");
    }

    public void displayResult(String[] head, String[][] body) {
        for (String title : head) {
            System.out.print(title + "\t");
        }
        System.out.print("\r\n");
        for (String[] row : body) {
            for (int colIndex = 0; colIndex < head.length; colIndex++) {
                System.out.print(row[colIndex] + "\t");
            }
            System.out.print("\r\n");
        }
    }

    /**
     * 将DB字段名转为java驼峰
     *
     * @param  key
     * @return
     */
    private String formatKeyname(String key) {
        String fmt = "";
        key = key.toLowerCase();
        if (key.indexOf("_") > 0) {
            String[] parts = key.split("_");
            for (int i = 0; i < parts.length; i++) {
                if (i == 0) {
                    fmt += parts[i];
                } else {
                    fmt += parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
                }
            }
        } else {
            fmt = key;
        }

        return fmt;
    }

    /**
     * 取得檢索結果，行單位List
     *
     * @param  query
     * @param  conn
     * @return
     */
    public List<Map<String, String>> excuteSelectList(String query, Connection conn) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] cols = getQueryColumns(result);
        List<Map<String, String>> rst = new ArrayList<Map<String, String>>();
        try {
            while (result.next()) {
                Map<String, String> values = new HashMap<String, String>();
                for (int i = 1; i <= cols.length; i++) {
                    // 字段属性
                    String key = result.getMetaData().getColumnName(i);
                    // 取得字段值
                    String value = result.getString(i);
                    // Map<数据库字段名,单元格值>
                    values.put(key, value);
                }
                rst.add(values);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(result, conn);
        }
        return rst;
    }

    /**
     * JDBC结果转为JTable表示内容
     *
     * @param  jdbcResult JDBC检索结果
     * @param  columns    table头字段信息
     * @return            String[][]
     */
    public Object[] getJTableInfo(String query, Connection conn) {
        Object[] returnResult = new Object[2];
        ResultSet result = null;
        try {
            // 执行SQL
            System.out.println(query);
            Statement st = conn.createStatement();
            result = st.executeQuery(query);
            // 取得列
            String[] cols = getQueryColumns(result);
            returnResult[0] = cols;
            List<String[]> rowData = new ArrayList<String[]>();
            // 取得行
            while (result.next()) {
                String[] colVals = new String[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    // 取得字段值
                    colVals[i] = result.getString(i + 1);
                }
                rowData.add(colVals);
            }
            // list转数组
            String[][] recoderList = new String[rowData.size()][cols.length];
            for (int rowIndex = 0; rowIndex < recoderList.length; rowIndex++) {
                recoderList[rowIndex] = rowData.get(rowIndex);
            }
            returnResult[1] = recoderList;
        } catch (SQLException e) {
            System.err.println("SQLException : " + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            closeConnection(result, conn);
        }

        return returnResult;
    }

    /**
     * 取得检索sql列
     *
     * @param  rs
     * @return
     */
    private String[] getQueryColumns(ResultSet rs) {
        String[] columns = null;

        try {
            int columnCounts = rs.getMetaData().getColumnCount();
            columns = new String[columnCounts];
            for (int i = 0; i < columnCounts; i++) {
                columns[i] = rs.getMetaData().getColumnName(i + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;
    }

    public void closeConnection(ResultSet rs, Connection conn) {
        try {
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
