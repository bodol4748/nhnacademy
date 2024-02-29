package com.nhnacademy.certificateissuingsystem.config;

import com.nhnacademy.certificateissuingsystem.Base;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        try {
            dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dataSource.setUrl("jdbc:mysql://133.186.244.96:3306/nhn_academy_20");
        dataSource.setUsername("nhn_academy_20");
        dataSource.setPassword("!bFM6ES*DaL@wLjV");

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(5);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

}