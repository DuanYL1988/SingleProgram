package com.application.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class Context {

    private static Properties properties;

    public static final String POINT = ".";

    public static final String SLASH = "\\";

    public static final String D_SLASH = "\\\\";

    public static final String SPACE = " ";

    public static final String COMMA = ",";

    public static final String UTF_8 = "UTF-8";

    /* 换行 */
    public static final String CRLF = "\r\n";
    /* 换行 */
    public static final String CRLF2 = "\r\n\r\n";
    /* 2空格 */
    public static final String SPACE2 = "  ";
    /* 4空格 */
    public static final String SPACE4 = "    ";
    /* 6空格 */
    public static final String SPACE6 = "      ";
    /* 8空格 */
    public static final String SPACE8 = "        ";
    /* 10空格 */
    public static final String SPACE10 = "          ";
    public static final String SPACE12 = "            ";

    /* */
    public static final String TBL_PK = "primaryKey";

    /* */
    public static final String TITLE = "{title}";
    /* */
    public static final String COLUMN_TITLE = "{columnTitle}";
    /* */
    public static final String SEARCH_TD = "{searchTd}";
    /* */
    public static final String COLUMN_INFO = "{columnInfo}";
    /* */
    public static final String BASE_URL = "{baseUrl}";

    /* swing layout空间取值KEY */
    /* SourcesCreater.java START */
    public static final String PROJECT = "project";
    public static final String LOCATION = "location";
    public static final String PAGEPATH = "pagepath";
    public static final String JSP_FLAG = "jspFlag";
    public static final String SPRINGBOOT_FLAG = "springbootFlag";
    public static final String JAVA_FLAG = "javaFlag";
    public static final String PACKAGE = "package";
    public static final String TABLE = "table";

    public static final String CONTROLLER = "controller";
    public static final String RESTFUL = "restful";
    public static final String HTML = "html";
    public static final String SERVICE = "service";
    public static final String DAO = "dao";
    public static final String MYBATIS = "mybatis";
    public static final String JPA = "jpa";
    public static final String SELECTALL = "selectAll";
    public static final String ORACLE = "oracle";
    public static final String MYSQL = "mysql";
    /* SourcesCreater.java END */

    /* RenamePictureFrame.java Start */
    public static final String BASE_DIR = "baseDirect";
    public static final String COPY_TO = "copyto";
    public static final String GAME_NAME = "GAME_NAME";
    public static final String FGO = "FGO";
    public static final String FEH = "FEH";
    /* RenamePictureFrame.java End */

    public static final String BEFORE = "fillBefore";
    public static final String AFTER = "fillAfter";

    public static final String DB_TYPE = "DatabaseType";

    public static String getProp(String key) {
        try {
            properties = null == properties ? Resources.getResourceAsProperties("project.properties") : properties;
            System.out.println(properties.get("DatabaseType").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key).toString();
    }

}
