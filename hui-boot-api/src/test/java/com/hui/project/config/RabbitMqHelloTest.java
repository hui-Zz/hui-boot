package com.hui.project.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
// 必须激活对应的项目配置
@ActiveProfiles("test")
public class RabbitMqHelloTest {
    @Autowired
    private HelloSender helloSender;
    @Autowired
    private HelloSender2 helloSender2;

    @Test
    public void hello() throws Exception {
        helloSender.send(666);
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            helloSender.send(i);
            helloSender2.send(i);
        }
    }
}