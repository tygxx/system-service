package com.yy.ds.system;

import com.yy.ds.system.user.dao.UserDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemServiceApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() {
        System.out.println(userDao.findByIdFetchRole(1L).toString());
    }

}
