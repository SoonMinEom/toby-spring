package com.likelion.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao awsUserDao() {

        return new UserDao(awsDataSource());
    }
    @Bean
    DataSource awsDataSource() {
        Map<String, String> evn = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(evn.get("DB_HOST"));
        dataSource.setUsername(evn.get("DB_NAME"));
        dataSource.setPassword(evn.get("DB_PASSWORD"));

        return dataSource;
    }


}
