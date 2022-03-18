package com.leichengyang.chapter7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiCoreSenderTest {

    @Autowired
    private ApiCoreSender apiCoreSender;

    @Test
    public void user() {
        apiCoreSender.user("用户管理");
    }

    @Test
    public void userQuery() {
        apiCoreSender.userQuery("查询用户");
    }
}