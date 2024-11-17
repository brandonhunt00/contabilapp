package com.pac.contabil.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://190.102.43.28:7556/dev")
                .username("root")
                .password("9730e4c7bf1e82b9b973")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
