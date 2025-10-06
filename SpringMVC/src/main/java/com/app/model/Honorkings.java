package com.app.model;

public class Honorkings extends ExpandCondition {
    /**
     *
     */
    private String id;

    /**
     * 名
     */
    private String name;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 分路
     */
    private String subvocation;

    /**
     * 1技能
     */
    private String skillcd1;

    /**
     * 2技能
     */
    private String skillcd2;

    /**
     * 大招
     */
    private String skillcd3;

    /**
     * 被动
     */
    private String skillcd4;

    /**
     * 图片地址
     */
    private String imgpath;

    /**
     * 皮肤
     */
    private String skin;

    /**
     * 设定
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得
     */
    public String getId() {
        return id;
    }

    /**
     * 设定名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得名
     */
    public String getName() {
        return name;
    }

    /**
     * 设定职业
     */
    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    /**
     * 取得职业
     */
    public String getVocation() {
        return vocation;
    }

    /**
     * 设定分路
     */
    public void setSubvocation(String subvocation) {
        this.subvocation = subvocation;
    }

    /**
     * 取得分路
     */
    public String getSubvocation() {
        return subvocation;
    }

    /**
     * 设定1技能
     */
    public void setSkillcd1(String skillcd1) {
        this.skillcd1 = skillcd1;
    }

    /**
     * 取得1技能
     */
    public String getSkillcd1() {
        return skillcd1;
    }

    /**
     * 设定2技能
     */
    public void setSkillcd2(String skillcd2) {
        this.skillcd2 = skillcd2;
    }

    /**
     * 取得2技能
     */
    public String getSkillcd2() {
        return skillcd2;
    }

    /**
     * 设定大招
     */
    public void setSkillcd3(String skillcd3) {
        this.skillcd3 = skillcd3;
    }

    /**
     * 取得大招
     */
    public String getSkillcd3() {
        return skillcd3;
    }

    /**
     * 设定被动
     */
    public void setSkillcd4(String skillcd4) {
        this.skillcd4 = skillcd4;
    }

    /**
     * 取得被动
     */
    public String getSkillcd4() {
        return skillcd4;
    }

    /**
     * 设定图片地址
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    /**
     * 取得图片地址
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * 设定皮肤
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }

    /**
     * 取得皮肤
     */
    public String getSkin() {
        return skin;
    }

}
