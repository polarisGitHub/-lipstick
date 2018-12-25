package com.polaris.he.lipstick.algorithm.color.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.polaris.he.lipstick.algorithm.color.data.Lab;
import com.polaris.he.lipstick.algorithm.color.data.Srgb;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@SpringBootTest
public class srgbLabConverterTest extends AbstractTestNGSpringContextTests {

    @Test
    public void transformTest() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String str = IOUtils.toString(resolver.getResource("classpath:data/color/colors.json").getInputStream(), Charset.forName("utf-8"));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode[] nodes = mapper.readValue(str, ObjectNode[].class);
        for (ObjectNode node : nodes) {
            Srgb sRgb = mapper.treeToValue(node.get("Srgb"), Srgb.class);
            Lab lab = mapper.treeToValue(node.get("lab"), Lab.class);
            Lab expectedLab = new RgbLabConverter().transform(sRgb);
            log.info("Srgb={},lab={},expetedLab={}", sRgb, lab, expectedLab);
        }
    }

}