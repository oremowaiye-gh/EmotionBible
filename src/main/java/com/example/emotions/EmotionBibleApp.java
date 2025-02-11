package com.example.emotions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
@Configuration
public class EmotionBibleApp {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public DataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:" + resourceLoader.getResource("classpath:emotionbible.db").getFile().getAbsolutePath());
        return dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmotionBibleApp.class, args);
    }
}