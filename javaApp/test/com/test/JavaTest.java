package com.test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.application.jdbc.MybatisBase;
import com.application.jdbc.MysqlUtil;
import com.application.jdbc.OracleUtil;
import com.application.jdbc.SqliteUtil;
import com.application.model.TableInfo;
import com.application.repository.TableInfoRepository;
import com.application.util.FileUtils;

@RunWith(JUnit4.class)
public class JavaTest {

    @Test
    public void testMysqlConnection() throws Exception {
        String query = "select * from hero";

        // 连接MySql
        MysqlUtil mysql = new MysqlUtil();
        Connection mysqlCon = mysql.getConnection();

        List<Map<String, String>> recoderList = mysql.excuteSelectList(query, mysqlCon);
        mysql.displayResult(recoderList);
    }

    @Test
    public void testOracleConnection() throws Exception {
        String query = "select * from servant";

        // 连接Oracle
        OracleUtil oracle = new OracleUtil();
        Connection oracleConn = oracle.getConnection();

        List<Map<String, String>> recoderList = oracle.excuteSelectList(query, oracleConn);
        oracle.displayResult(recoderList);
    }

    @Test
    public void testSqliteConnection() throws Exception {
        String query = "select id,name,class_type from servant limit 20,10";
        // 连接Sqlite
        SqliteUtil sqlite = new SqliteUtil();
        Connection sqliteConn = sqlite.getConnection();

        List<Map<String, String>> recoderList = sqlite.excuteSelectList(query, sqliteConn);
        sqlite.displayResult(recoderList);
    }

    @Test
    public void testGetJTableData() throws Exception {
        String tableName = "HERO";
        TableInfo cond = new TableInfo();
        cond.setPageSize(100);
        cond.setTableName(tableName);
        cond.setColListDisableFlag(1);
        MybatisBase conn = new MybatisBase();
        TableInfoRepository dao = conn.session.getMapper(TableInfoRepository.class);
        List<TableInfo> selectColumns = dao.selectByDto(cond);

        String quary = "SELECT ";
        for (TableInfo column : selectColumns) {
            quary += column.getColName() + " AS " + column.getColNameCh() + ",";
        }
        quary = quary.substring(0, quary.length() - 1);
        quary += " from " + tableName + " WHERE NAME LIKE '琳%' ";
        // // 连接Sqlite
        SqliteUtil sqlite = new SqliteUtil();
        Connection sqliteConn = sqlite.getConnection();

        Object[] jtableInfo = sqlite.getJTableInfo(quary, sqliteConn);
        String[] head = (String[]) jtableInfo[0];
        String[][] body = (String[][]) jtableInfo[1];

        sqlite.displayResult(head, body);
        Assert.assertTrue(selectColumns.size() == head.length);
        Assert.assertTrue(body.length > 0);
    }

    @Test
    public void testReplace() {
        String text = "称号UNIQUE";
        System.out.println(text.replaceAll("[a-zA-Z]", ""));
    }

    @Test
    public void testCreateFolder() {
        FileUtils.createFolder("D:\\Project\\output\\javaApp\\src\\com\\application\\controller");
    }

}
