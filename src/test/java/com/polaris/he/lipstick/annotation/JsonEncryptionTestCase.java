package com.polaris.he.lipstick.annotation;

import com.polaris.he.application.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * User: hexie
 * Date: 2019-01-29 20:24
 * Description:
 */
@Slf4j
@SpringBootTest
public class JsonEncryptionTestCase extends AbstractTestNGSpringContextTests {


    @Test
    public void test() {
        EncryptionTestData data = new EncryptionTestData("1", "2", 3, 4);
        String content = JsonUtils.toJsonString(data);
        EncryptionTestData revert = JsonUtils.toJavaObject(content, EncryptionTestData.class);
        log.info("json string={}", content);
        log.info("java object={}", revert);
        Assert.assertEquals(data, revert);
    }

}