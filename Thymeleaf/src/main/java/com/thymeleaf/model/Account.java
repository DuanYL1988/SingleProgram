package com.thymeleaf.model;

public class Account extends ExpandCondition {
    /**
     * '涓婚敭'
     */
    private Integer id;

    /**
     * '鐢ㄦ埛鍚�'
     */
    private String loginName;

    /**
     * '鐢ㄦ埛鍚�'
     */
    private String username;

    /**
     * '瀵嗙爜'
     */
    private String password;

    /**
     * '鏉冮檺'
     */
    private String roleId;

    /**
     * '搴旂敤'
     */
    private String application;

    /**
     * '鍏徃'
     */
    private String company;

    /**
     * '缁勭粐'
     */
    private String groupName;

    /**
     * '鐢佃瘽'
     */
    private String telphone;

    /**
     * '澶村儚(鏈湴)'
     */
    private String faceLocal;

    /**
     * '澶村儚鍦板潃'
     */
    private String faceUrl;

    private String token;

    /**
     * 设定'涓婚敭'
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 取得'涓婚敭'
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设定'鐢ㄦ埛鍚�'
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 取得'鐢ㄦ埛鍚�'
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设定'鐢ㄦ埛鍚�'
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 取得'鐢ㄦ埛鍚�'
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设定'瀵嗙爜'
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得'瀵嗙爜'
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设定'鏉冮檺'
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 取得'鏉冮檺'
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设定'搴旂敤'
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * 取得'搴旂敤'
     */
    public String getApplication() {
        return application;
    }

    /**
     * 设定'鍏徃'
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 取得'鍏徃'
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设定'缁勭粐'
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 取得'缁勭粐'
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设定'鐢佃瘽'
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * 取得'鐢佃瘽'
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设定'澶村儚(鏈湴)'
     */
    public void setFaceLocal(String faceLocal) {
        this.faceLocal = faceLocal;
    }

    /**
     * 取得'澶村儚(鏈湴)'
     */
    public String getFaceLocal() {
        return faceLocal;
    }

    /**
     * 设定'澶村儚鍦板潃'
     */
    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    /**
     * 取得'澶村儚鍦板潃'
     */
    public String getFaceUrl() {
        return faceUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
