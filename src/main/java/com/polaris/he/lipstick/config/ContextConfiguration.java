package com.polaris.he.lipstick.config;

import com.polaris.he.lipstick.dao.Dao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: hexie
 * Date: 2018-12-08 16:17
 * Description:
 */
@Configuration
@EnableCaching
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan(basePackages = "com.polaris.he.lipstick.dao", markerInterface = Dao.class)
public class ContextConfiguration {
}