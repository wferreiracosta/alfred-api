package com.wferreiracosta.alfred.config;

import com.wferreiracosta.alfred.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hml")
public class HmlConfig {
    @Autowired
    private DataBaseService dbService;

    @Bean
    public boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }
}
