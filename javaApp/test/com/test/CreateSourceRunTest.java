package com.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.application.jdbc.MybatisBase;
import com.application.model.Hero;
import com.application.repository.HeroRepository;

@RunWith(JUnit4.class)
public class CreateSourceRunTest {

    SqlSession session;

    HeroRepository heroDao;

    @Before
    public void setUp() {
        System.out.println("Create Session");
        MybatisBase conn = new MybatisBase();
        session = conn.getSession();
        heroDao = session.getMapper(HeroRepository.class);
    }

    @After
    public void doFinal() {
        System.out.println("Close Session");
        if (null != session) {
            session.close();
        }
    }

    @Test
    public void testUpdateMethods() {
        // 复写拷贝数据
        Hero copyData = heroDao.selectOneById(250);
        // 重设ID插入
        copyData.setId(null);
        String newTitleName = copyData.getTitleName() + "_Test";
        copyData.setTitleName(newTitleName);
        heroDao.insert(copyData);

        // 取得最大ID, 且验证自定义检索功能
        Hero custDto = new Hero();
        custDto.setSelectQuary(" max(id) as id ");
        Hero custHero = heroDao.customQuary(custDto).get(0);
        int newestId = custHero.getId();
        System.out.println(newestId);

        // 更新
        String newName = copyData.getName() + "_Test";
        copyData.setName(newName);
        copyData.setId(newestId);
        heroDao.update(copyData);

        // 取得DB更新后最新的数据
        Hero updatedData = heroDao.selectOneById(newestId);
        // 验证是否是更新的数据
        Assert.assertEquals(updatedData.getTitleName(), newTitleName);
        Assert.assertEquals(updatedData.getName(), newName);

        // 删除
        int deleteCount = heroDao.delete(newestId);
        Hero deleted = heroDao.customQuary(custDto).get(0);
        int maxId = deleted.getId();
        // 删除后最大的ID - 1
        Assert.assertEquals(maxId, newestId - 1);

        if (deleteCount == 0) {
            // 没有删除数据时 业务回滚
            session.rollback();
        }
    }

    @Test
    public void testCommit() {
        Hero data = heroDao.selectOneById(627);
        System.out.println(data.getWeaponType());
    }
}
