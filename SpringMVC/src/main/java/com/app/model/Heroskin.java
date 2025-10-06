package com.app.model;

public class Heroskin extends ExpandCondition {
    /**
     *
     */
    private String id;

    /**
     * 英雄
     */
    private Integer heroid;

    /**
     * 名称
     */
    private String heroname;

    /**
     *
     */
    private Integer skinindex;

    /**
     * 皮肤名
     */
    private String skinname;

    /**
     * 图片
     */
    private String imgurl;

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
     * 设定英雄
     */
    public void setHeroid(Integer heroid) {
        this.heroid = heroid;
    }

    /**
     * 取得英雄
     */
    public Integer getHeroid() {
        return heroid;
    }

    /**
     * 设定名称
     */
    public void setHeroname(String heroname) {
        this.heroname = heroname;
    }

    /**
     * 取得名称
     */
    public String getHeroname() {
        return heroname;
    }

    /**
     * 设定
     */
    public void setSkinindex(Integer skinindex) {
        this.skinindex = skinindex;
    }

    /**
     * 取得
     */
    public Integer getSkinindex() {
        return skinindex;
    }

    /**
     * 设定皮肤名
     */
    public void setSkinname(String skinname) {
        this.skinname = skinname;
    }

    /**
     * 取得皮肤名
     */
    public String getSkinname() {
        return skinname;
    }

    /**
     * 设定图片
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 取得图片
     */
    public String getImgurl() {
        return imgurl;
    }

}
