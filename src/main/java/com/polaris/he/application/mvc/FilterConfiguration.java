package com.polaris.he.application.mvc;

import com.polaris.he.application.mvc.filter.MdcFilter;
import com.polaris.he.application.mvc.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;

/**
 * User: hexie
 * Date: 2019-01-11 22:05
 * Description:
 */
@Configuration
public class FilterConfiguration {


    @Bean
    public FilterRegistrationBean mdcFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("mdc");
        registrationBean.setFilter(new MdcFilter());
        registrationBean.setOrder(1);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("jwtFilter");
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setOrder(2);
        registrationBean.setUrlPatterns(Collections.singletonList("/api/*"));
        return registrationBean;
    }
}