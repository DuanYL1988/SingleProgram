package com.thymeleaf;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thymeleaf.model.Account;
import com.thymeleaf.model.FireemblemHero;
import com.thymeleaf.model.TableColumn;
import com.thymeleaf.repository.AccountRepository;
import com.thymeleaf.repository.FireemblemHeroRepository;
import com.thymeleaf.repository.TableColumnRepository;

public class DaoTester extends BaseTester {

    @Autowired
    AccountRepository testDao;

    @Autowired
    FireemblemHeroRepository feDao;

    @Autowired
    TableColumnRepository columnDao;

    @Test
    public void testSelectOne() {
        Account user = testDao.selectOneById(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void testFeDao() {
        FireemblemHero condition = new FireemblemHero();
        condition.setName("Ike");
        List<FireemblemHero> result = feDao.selectByDto(condition);
        System.out.println(result.size());
    }

    @Test
    public void testColumnDao() {
        TableColumn condition = new TableColumn();
        condition.setTableName("FIREEMBLEM_HERO");
        condition.setListVisable("●");
        List<TableColumn> result = columnDao.selectByDto(condition);
        System.out.println(result.size());
    }

}
