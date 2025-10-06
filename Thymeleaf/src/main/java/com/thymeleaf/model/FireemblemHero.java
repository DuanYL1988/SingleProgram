package com.thymeleaf.model;

import javax.validation.constraints.NotEmpty;

public class FireemblemHero extends ExpandCondition {
    /**
     * '涓婚敭'
     */
    private Integer id;

    /**
     * '绉板彿'
     */
    @NotEmpty(message = "必须输入称号")
    private String titleName;

    /**
     * '鍚�'
     */
    private String name;

    /**
     * '涓枃鍚�'
     */
    private String nameCn;

    /**
     * '鏃ユ枃鍚�'
     */
    private String nameJp;

    /**
     * '绔嬬粯鍚�'
     */
    private String imgName;

    /**
     * '澶村儚'
     */
    private String faceImgUrl;

    /**
     * '鏅�氱珛缁�'
     */
    private String normalImgUrl;

    /**
     * '鏀诲嚮绔嬬粯'
     */
    private String attactImgUrl;

    /**
     * '蹇呮潃绔嬬粯'
     */
    private String extraImgUrl;

    /**
     * '澶Х牬绔嬬粯'
     */
    private String breakImgUrl;

    /**
     * '鐢熷懡鍊�'
     */
    private String hp;

    /**
     * '鏀诲嚮'
     */
    private String attact;

    /**
     * '閫熷害'
     */
    private String speed;

    /**
     * '闃插尽'
     */
    private String def;

    /**
     * '榄旈槻'
     */
    private String mdf;

    /**
     * '鎶�鑳�'
     */
    private String skillA;

    /**
     * '鎶�鑳�'
     */
    private String skillB;

    /**
     * '鎶�鑳�'
     */
    private String skillC;

    /**
     * '鍦Ｅ嵃鎶�鑳�'
     */
    private String skillD;

    /**
     * '鏀彺鎶�鑳�'
     */
    private String skillS;

    /**
     * '蹇呮潃鎶�鑳�'
     */
    private String skillE;

    /**
     * '绐佺牬鏋侀檺'
     */
    private String limitPlus;

    /**
     * '绁為緳涔嬭姳'
     */
    private String dragonFlower;

    /**
     * '鏀彺瀵硅薄'
     */
    private String supportMate;

    /**
     * '绁濈'
     */
    private String blessing;

    /**
     * '鍏电'
     */
    private String moveType;

    /**
     * '姝﹀櫒鍚�'
     */
    private String weapon;

    /**
     * '姝﹀櫒绫诲瀷'
     */
    private String weaponType;

    /**
     * '瑙掕壊浣滃搧'
     */
    private String origin;

    /**
     * '瀹濈彔棰滆壊'
     */
    private String color;

    /**
     * '绉嶆棌'
     */
    private String race;

    /**
     * '鑻遍泟绫诲瀷(杩炵考/浼犳壙)'
     */
    private String heroType;

    /**
     * '闃熶紞'
     */
    private String team;

    /**
     * '鐗规晥鏍囩'
     */
    private String specTag;

    /**
     * '鍠滄'
     */
    private String favorite;

    /**
     * '璇勪环绛夌骇'
     */
    private String heroRank;

    /**
     * '鐧诲綍鏃ユ湡'
     */
    private String releaseDate;

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
     * 设定'绉板彿'
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    /**
     * 取得'绉板彿'
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * 设定'鍚�'
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得'鍚�'
     */
    public String getName() {
        return name;
    }

    /**
     * 设定'涓枃鍚�'
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    /**
     * 取得'涓枃鍚�'
     */
    public String getNameCn() {
        return nameCn;
    }

    /**
     * 设定'鏃ユ枃鍚�'
     */
    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

    /**
     * 取得'鏃ユ枃鍚�'
     */
    public String getNameJp() {
        return nameJp;
    }

    /**
     * 设定'绔嬬粯鍚�'
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * 取得'绔嬬粯鍚�'
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设定'澶村儚'
     */
    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    /**
     * 取得'澶村儚'
     */
    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    /**
     * 设定'鏅�氱珛缁�'
     */
    public void setNormalImgUrl(String normalImgUrl) {
        this.normalImgUrl = normalImgUrl;
    }

    /**
     * 取得'鏅�氱珛缁�'
     */
    public String getNormalImgUrl() {
        return normalImgUrl;
    }

    /**
     * 设定'鏀诲嚮绔嬬粯'
     */
    public void setAttactImgUrl(String attactImgUrl) {
        this.attactImgUrl = attactImgUrl;
    }

    /**
     * 取得'鏀诲嚮绔嬬粯'
     */
    public String getAttactImgUrl() {
        return attactImgUrl;
    }

    /**
     * 设定'蹇呮潃绔嬬粯'
     */
    public void setExtraImgUrl(String extraImgUrl) {
        this.extraImgUrl = extraImgUrl;
    }

    /**
     * 取得'蹇呮潃绔嬬粯'
     */
    public String getExtraImgUrl() {
        return extraImgUrl;
    }

    /**
     * 设定'澶Х牬绔嬬粯'
     */
    public void setBreakImgUrl(String breakImgUrl) {
        this.breakImgUrl = breakImgUrl;
    }

    /**
     * 取得'澶Х牬绔嬬粯'
     */
    public String getBreakImgUrl() {
        return breakImgUrl;
    }

    /**
     * 设定'鐢熷懡鍊�'
     */
    public void setHp(String hp) {
        this.hp = hp;
    }

    /**
     * 取得'鐢熷懡鍊�'
     */
    public String getHp() {
        return hp;
    }

    /**
     * 设定'鏀诲嚮'
     */
    public void setAttact(String attact) {
        this.attact = attact;
    }

    /**
     * 取得'鏀诲嚮'
     */
    public String getAttact() {
        return attact;
    }

    /**
     * 设定'閫熷害'
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * 取得'閫熷害'
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * 设定'闃插尽'
     */
    public void setDef(String def) {
        this.def = def;
    }

    /**
     * 取得'闃插尽'
     */
    public String getDef() {
        return def;
    }

    /**
     * 设定'榄旈槻'
     */
    public void setMdf(String mdf) {
        this.mdf = mdf;
    }

    /**
     * 取得'榄旈槻'
     */
    public String getMdf() {
        return mdf;
    }

    /**
     * 设定'鎶�鑳�'
     */
    public void setSkillA(String skillA) {
        this.skillA = skillA;
    }

    /**
     * 取得'鎶�鑳�'
     */
    public String getSkillA() {
        return skillA;
    }

    /**
     * 设定'鎶�鑳�'
     */
    public void setSkillB(String skillB) {
        this.skillB = skillB;
    }

    /**
     * 取得'鎶�鑳�'
     */
    public String getSkillB() {
        return skillB;
    }

    /**
     * 设定'鎶�鑳�'
     */
    public void setSkillC(String skillC) {
        this.skillC = skillC;
    }

    /**
     * 取得'鎶�鑳�'
     */
    public String getSkillC() {
        return skillC;
    }

    /**
     * 设定'鍦Ｅ嵃鎶�鑳�'
     */
    public void setSkillD(String skillD) {
        this.skillD = skillD;
    }

    /**
     * 取得'鍦Ｅ嵃鎶�鑳�'
     */
    public String getSkillD() {
        return skillD;
    }

    /**
     * 设定'鏀彺鎶�鑳�'
     */
    public void setSkillS(String skillS) {
        this.skillS = skillS;
    }

    /**
     * 取得'鏀彺鎶�鑳�'
     */
    public String getSkillS() {
        return skillS;
    }

    /**
     * 设定'蹇呮潃鎶�鑳�'
     */
    public void setSkillE(String skillE) {
        this.skillE = skillE;
    }

    /**
     * 取得'蹇呮潃鎶�鑳�'
     */
    public String getSkillE() {
        return skillE;
    }

    /**
     * 设定'绐佺牬鏋侀檺'
     */
    public void setLimitPlus(String limitPlus) {
        this.limitPlus = limitPlus;
    }

    /**
     * 取得'绐佺牬鏋侀檺'
     */
    public String getLimitPlus() {
        return limitPlus;
    }

    /**
     * 设定'绁為緳涔嬭姳'
     */
    public void setDragonFlower(String dragonFlower) {
        this.dragonFlower = dragonFlower;
    }

    /**
     * 取得'绁為緳涔嬭姳'
     */
    public String getDragonFlower() {
        return dragonFlower;
    }

    /**
     * 设定'鏀彺瀵硅薄'
     */
    public void setSupportMate(String supportMate) {
        this.supportMate = supportMate;
    }

    /**
     * 取得'鏀彺瀵硅薄'
     */
    public String getSupportMate() {
        return supportMate;
    }

    /**
     * 设定'绁濈'
     */
    public void setBlessing(String blessing) {
        this.blessing = blessing;
    }

    /**
     * 取得'绁濈'
     */
    public String getBlessing() {
        return blessing;
    }

    /**
     * 设定'鍏电'
     */
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    /**
     * 取得'鍏电'
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * 设定'姝﹀櫒鍚�'
     */
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /**
     * 取得'姝﹀櫒鍚�'
     */
    public String getWeapon() {
        return weapon;
    }

    /**
     * 设定'姝﹀櫒绫诲瀷'
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * 取得'姝﹀櫒绫诲瀷'
     */
    public String getWeaponType() {
        return weaponType;
    }

    /**
     * 设定'瑙掕壊浣滃搧'
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * 取得'瑙掕壊浣滃搧'
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * 设定'瀹濈彔棰滆壊'
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 取得'瀹濈彔棰滆壊'
     */
    public String getColor() {
        return color;
    }

    /**
     * 设定'绉嶆棌'
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * 取得'绉嶆棌'
     */
    public String getRace() {
        return race;
    }

    /**
     * 设定'鑻遍泟绫诲瀷(杩炵考/浼犳壙)'
     */
    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    /**
     * 取得'鑻遍泟绫诲瀷(杩炵考/浼犳壙)'
     */
    public String getHeroType() {
        return heroType;
    }

    /**
     * 设定'闃熶紞'
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * 取得'闃熶紞'
     */
    public String getTeam() {
        return team;
    }

    /**
     * 设定'鐗规晥鏍囩'
     */
    public void setSpecTag(String specTag) {
        this.specTag = specTag;
    }

    /**
     * 取得'鐗规晥鏍囩'
     */
    public String getSpecTag() {
        return specTag;
    }

    /**
     * 设定'鍠滄'
     */
    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    /**
     * 取得'鍠滄'
     */
    public String getFavorite() {
        return favorite;
    }

    /**
     * 设定'璇勪环绛夌骇'
     */
    public void setHeroRank(String heroRank) {
        this.heroRank = heroRank;
    }

    /**
     * 取得'璇勪环绛夌骇'
     */
    public String getHeroRank() {
        return heroRank;
    }

    /**
     * 设定'鐧诲綍鏃ユ湡'
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 取得'鐧诲綍鏃ユ湡'
     */
    public String getReleaseDate() {
        return releaseDate;
    }

}
