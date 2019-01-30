package com.polaris.he.lipstick.config.datasource;

import com.polaris.he.lipstick.dao.Interceptor.MybatisSqlLogInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: hexie
 * Date: 2019-01-30 23:37
 *
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration construnct ObjectProvider
 * Description:
 */
@Configuration
public class MybatisInterceptorConfiguration {

    @Bean
    public Interceptor sqlLogInterceptor() {
        return new MybatisSqlLogInterceptor();
    }
}