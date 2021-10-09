package com.yy.ds.system;

import com.alibaba.fastjson.JSONObject;
import com.yy.ds.system.user.domain.User;
import com.yy.ds.system.user.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = userService.getById(1L);
        System.out.println(JSONObject.toJSONString(user));
    }

}
