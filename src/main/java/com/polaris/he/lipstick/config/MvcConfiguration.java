package com.polaris.he.lipstick.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-08 11:15
 * Description:
 */
@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper json = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL).build();
        converters.add(new MappingJackson2HttpMessageConverter(json));
    }
}