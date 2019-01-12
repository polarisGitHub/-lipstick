package com.polaris.he.lipstick.config.mvc;

import com.polaris.he.lipstick.config.mvc.filter.MdcFilter;
import com.polaris.he.lipstick.config.mvc.filter.WeixinUserInfoFilter;
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
    public FilterRegistrationBean weixinFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("weixinUserInfo");
        registrationBean.setFilter(new WeixinUserInfoFilter());
        registrationBean.setOrder(2);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }
}