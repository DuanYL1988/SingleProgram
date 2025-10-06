package com.application.dto;

import java.util.List;

public class Table {

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 表明
     */
    private String name;

    /**
     * 主键
     */
    private Column pkey;

    /**
     * 逻辑主键
     */
    private List<Column> uniqueKey;

    /**
     * 字段集合
     */
    private List<Column> columnList;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Column getPkey() {
        return pkey;
    }

    public void setPkey(Column pkey) {
        this.pkey = pkey;
    }

    public List<Column> getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(List<Column> uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public Column getColumnInstance() {
        return new Column();
    }

    /**
     * 字段情报
     *
     * @author dylsw
     *
     */
    public class Column {
        /**
         * java变量名
         */
        private String sourceName;

        /**
         * 逻辑名
         */
        private String logicName;

        /**
         * DB字段物理名
         */
        private String columnName;

        /**
         * java变量属性
         */
        private String javaType;

        /**
         * DB字段属性
         */
        private String columnType;

        /**
         * DB字段长度
         */
        private Integer length;

        private String div;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getLogicName() {
            return logicName;
        }

        public void setLogicName(String logicName) {
            this.logicName = logicName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public String getDiv() {
            return div;
        }

        public void setDiv(String div) {
            this.div = div;
        }

    }

}
