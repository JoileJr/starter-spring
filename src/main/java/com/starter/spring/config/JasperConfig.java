package com.starter.spring.config;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JasperConfig {
    
    @Bean
    public Connection connection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

}
