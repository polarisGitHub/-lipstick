package com.polaris.he.lipstick.algorithm.color.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.polaris.he.lipstick.algorithm.color.data.Rgb;
import com.polaris.he.lipstick.algorithm.color.data.Xyz;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;


@Slf4j
public class RgbXyzConverterTest extends AbstractTestNGSpringContextTests {

    @Test
    public void transformTest() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String str = IOUtils.toString(resolver.getResource("classpath:data/color/colors.json").getInputStream(), Charset.forName("utf-8"));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode[] nodes = mapper.readValue(str, ObjectNode[].class);
        for (ObjectNode node : nodes) {
            Rgb rgb = mapper.treeToValue(node.get("rgb"), Rgb.class);
            Xyz lab = mapper.treeToValue(node.get("xyz"), Xyz.class);
            Xyz expectedXyz = new RgbXyzConverter().transform(rgb);
            log.info("rgb={},lab={},expetedLab={}", rgb, lab, expectedXyz);
        }
    }
}