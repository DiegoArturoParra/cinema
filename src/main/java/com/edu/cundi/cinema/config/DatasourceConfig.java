package com.edu.cundi.cinema.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {
    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://103.23.60.189:5432/libreria").username("postgres").password("diego").build();
    }
}