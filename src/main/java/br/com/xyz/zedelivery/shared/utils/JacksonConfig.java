package br.com.xyz.zedelivery.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public static ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();

        mapper.registerModule(new JtsModule());
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}