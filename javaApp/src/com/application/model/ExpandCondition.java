package com.application.model;

/**
 * @author dylsw
 *
 */
/**
 * @author dylsw
 *
 */
public class ExpandCondition {

    private String selectQuary;

    private String joinPart;

    private String condition;

    private String orderBy;

    private String groupBy;

    private String having;

    private String unionAll;

    private int count;

    private String type;

    private int pageNo = 1;

    private int startRow;

    private int pageSize = 50;

    private int maxPage;

    public String getSelectQuary() {
        return selectQuary;
    }

    public void setSelectQuary(String selectQuary) {
        this.selectQuary = selectQuary;
    }

    public String getJoinPart() {
        return joinPart;
    }

    public void setJoinPart(String joinPart) {
        this.joinPart = joinPart;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getHaving() {
        return having;
    }

    public void setHaving(String having) {
        this.having = having;
    }

    public int getCount() {
        return count;
    }

    public String getUnionAll() {
        return unionAll;
    }

    public void setUnionAll(String unionAll) {
        this.unionAll = unionAll;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

}
