package com.knet.chatsecurity.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class GlobalConfig {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public ObjectMapper objectMapper(){
        return objectMapper;
    }

}
