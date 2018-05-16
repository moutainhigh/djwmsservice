package com.dj.com.wms.commons.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 15:08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring-mvc.xml",
        "classpath:config/spring/spring-mybatis.xml",
        "classpath:config/spring/spring-redis.xml",
        "classpath:config/spring/spring-other.xml",
        "classpath:config/params-config.xml",
        "classpath:config/spring/spring-rabbitmq-*.xml"})
public class BaseTest {
}
