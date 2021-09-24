package com.yy.ds.system;

import com.alibaba.fastjson.JSONObject;
import com.yy.ds.system.user.dao.UserDao;
import com.yy.ds.system.user.domain.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemServiceApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() {
        User user = userDao.findByUsernameFetchRole("ty");
        // 没有查询出来的属性置为null，否则序列化会有报错no-session
        user.getRoleList().forEach(r -> {
            r.setPermissionList(null);
            r.setUserList(null);
        });
        System.out.println(JSONObject.toJSONString(user));
    }

}
