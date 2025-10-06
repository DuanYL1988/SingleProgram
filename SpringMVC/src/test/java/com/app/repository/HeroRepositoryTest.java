package com.app.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.model.Hero;
import com.app.model.Menu;
import com.app.model.Servant;
import com.app.service.HeroService;
import com.app.util.CommonUtils;
import com.app.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml" })
public class HeroRepositoryTest {

    @Autowired
    MenuRepository menuRepo;

    @Autowired
    HeroRepository heroRepo;

    @Autowired
    HeroService heroService;

    @Autowired
    OracleCommonRepository oracleRepository;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    CommonUtils commonUtil;

    @Autowired
    ServantRepository servantRepo;

    @Test
    public void testSearchServant() {
        // List<Servant> list = servantRepo.selectByDto(new Servant());
        Servant result = servantRepo.selectOneById(137 + "");
        System.out.println(result.getImgName());
    }

    @Test
    public void testGetHeroInfo() {
        int id = 137;
        Hero hero = heroRepo.selectOneById(id);
        System.out.println(hero.getImgName());
    }

    @Test
    public void testGetNextvalFromSeq() {
        System.out.println(oracleRepository.getNextval("SEQ_HERO_MASTER"));
        System.out.println(oracleRepository.getCurrentval("SEQ_HERO_MASTER"));
    }

    @Test
    public void testUpdate() {
        Hero updObj = new Hero();
        updObj.setId(256);
        updObj.setNormalImgUrl("https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/5/5f/Karla_Sun-Piercing_Steel_Face.webp/revision/latest?cb=20220117080252");
        updObj.setAttactImgUrl("https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/a/ab/Karla_Sun-Piercing_Steel_BtlFace.webp/revision/latest?cb=20220117080202");
        updObj.setExtraImgUrl("https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/8/88/Karla_Sun-Piercing_Steel_BtlFace_C.webp/revision/latest?cb=20220117080233");
        updObj.setBreakImgUrl("https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/0/08/Karla_Sun-Piercing_Steel_BtlFace_D.webp/revision/latest?cb=20220117080242");
        heroRepo.update(updObj);
    }

    @Test
    public void testCustom() {
        Hero custom = new Hero();
        custom.setSelectQuary(" sub.count,main.* ");
        custom.setJoinPart(" main left join(select min(id) as id,name as name,count(name) as count from hero group by name order by id) sub on main.id = sub.id");
        custom.setCondition(" and main.name = '琳迪斯'");
        custom.setOrderBy(" main.master_id,main.id");
        List<Hero> rst = heroRepo.customQuary(custom);
        System.out.println(rst.size());
    }

    /**
     * Hero数据转换成Json
     */
    @Test
    public void createJsonData() {
        Hero condition = new Hero();

        List<Hero> heroList = heroRepo.selectByDto(condition);
        System.out.println(jsonUtil.praseObjToJson(heroList));
    }

    @Test
    public void createFgoJson() {
        Servant condition = new Servant();

        List<Servant> servantList = servantRepo.selectByDto(condition);
        System.out.println("var jsonData = ");
        System.out.println(jsonUtil.praseObjToJson(servantList));
        System.out.println(";");
    }

    @Test
    public void testMenuRepo() {
        Menu condition = new Menu();
        condition.setCategore("火纹");
        List<Menu> menuList = menuRepo.selectByDto(null);
        assertTrue(menuList.size() > 0);

        condition.setId(1);
        condition.setMenuLevel("2");
        menuRepo.update(condition);

        String jsonText = jsonUtil.praseObjToJson(menuList);
        System.out.println(jsonText);
    }

}
