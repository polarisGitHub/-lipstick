package com.polaris.he.lipstick.config.mvc;

import com.polaris.he.lipstick.common.utils.JsonUtils;
import com.polaris.he.lipstick.config.mvc.argument.UserInfoArgumentResolver;
import com.polaris.he.lipstick.config.mvc.converter.StringToCosmeticsEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(JsonUtils.getObjectMapper()));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserInfoArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToCosmeticsEnumConverter());
    }
}