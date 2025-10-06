package com.application.model;

public class Menu extends ExpandCondition {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 应用
     */
    private String application;

    /**
     * 公司
     */
    private String company;

    /**
     * 组织
     */
    private String compGroup;

    /**
     * 位置
     */
    private String location;

    /**
     * 名称
     */
    private String name;

    /**
     * 父菜单
     */
    private String parentId;

    /**
     * 静态页面连接
     */
    private String htmlUrl;

    /**
     * 动态链接
     */
    private String serverUrl;

    /**
     * 链接
     */
    private String vueUrl;

    /**
     * 参数
     */
    private String appParam;

    /**
     * 图标
     */
    private String iconUrl;

    /**
     * 排序
     */
    private Integer itemSort;

    /**
     * 设定主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 取得主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设定应用
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * 取得应用
     */
    public String getApplication() {
        return application;
    }

    /**
     * 设定公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 取得公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设定组织
     */
    public void setCompGroup(String compGroup) {
        this.compGroup = compGroup;
    }

    /**
     * 取得组织
     */
    public String getCompGroup() {
        return compGroup;
    }

    /**
     * 设定位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 取得位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设定名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设定父菜单
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 取得父菜单
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设定静态页面连接
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * 取得静态页面连接
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * 设定动态链接
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * 取得动态链接
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * 设定链接
     */
    public void setVueUrl(String vueUrl) {
        this.vueUrl = vueUrl;
    }

    /**
     * 取得链接
     */
    public String getVueUrl() {
        return vueUrl;
    }

    /**
     * 设定参数
     */
    public void setAppParam(String appParam) {
        this.appParam = appParam;
    }

    /**
     * 取得参数
     */
    public String getAppParam() {
        return appParam;
    }

    /**
     * 设定图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 取得图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设定排序
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    /**
     * 取得排序
     */
    public Integer getItemSort() {
        return itemSort;
    }

}
