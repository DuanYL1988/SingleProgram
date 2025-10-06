package com.thymeleaf.model;

public class TableColumn extends ExpandCondition {
    /**
     * 表名
     */
    public String tableName;

    /**
     * 字段伦理名
     */
    public String columnName;

    /**
     * 字段物理名
     */
    public String comment;

    /**
     * 字段属性
     */
    public String columnType;

    /**
     * 字段长度
     */
    public String columnLength;

    /**
     * 列表显示长度
     */
    public String listLength;

    /**
     * 页面输入类型
     */
    public String inputType;

    /**
     * 主键
     */
    public String pkFlag;

    /**
     * 唯一键
     */
    public String uniqueFlag;

    /**
     * 非空
     */
    public String notnullFlag;

    /**
     * 编辑
     */
    public String listEditable;

    /**
     * 检索条件
     */
    public String fifterable;

    /**
     * 初始编辑
     */
    public String insertable;

    /**
     * 更新
     */
    public String updateable;

    /**
     * 列表可见
     */
    public String listVisable;

    /**
     * 驼峰命名
     */
    public String propertyName;

    /**
     * CODE
     */
    public String code;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setListLength(String listLength) {
        this.listLength = listLength;
    }

    public String getListLength() {
        return listLength;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setPkFlag(String pkFlag) {
        this.pkFlag = pkFlag;
    }

    public String getPkFlag() {
        return pkFlag;
    }

    public void setUniqueFlag(String uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }

    public String getUniqueFlag() {
        return uniqueFlag;
    }

    public void setNotnullFlag(String notnullFlag) {
        this.notnullFlag = notnullFlag;
    }

    public String getNotnullFlag() {
        return notnullFlag;
    }

    public void setListEditable(String listEditable) {
        this.listEditable = listEditable;
    }

    public String getListEditable() {
        return listEditable;
    }

    public void setFifterable(String fifterable) {
        this.fifterable = fifterable;
    }

    public String getFifterable() {
        return fifterable;
    }

    public void setInsertable(String insertable) {
        this.insertable = insertable;
    }

    public String getInsertable() {
        return insertable;
    }

    public void setUpdateable(String updateable) {
        this.updateable = updateable;
    }

    public String getUpdateable() {
        return updateable;
    }

    public void setListVisable(String listVisable) {
        this.listVisable = listVisable;
    }

    public String getListVisable() {
        return listVisable;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
