package com.example.emotions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
public class EmotionBibleApp {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:C:/Users/oluwaseun.mowaiye/Documents/EmotionBible/src/main/resources/emotionbible.db");
        dataSource.setUrl("jdbc:sqlite:src/main/resources/emotionbible.db");
        return dataSource;
    }



    public static void main(String[] args) {
        SpringApplication.run(EmotionBibleApp.class, args);


    }
}
