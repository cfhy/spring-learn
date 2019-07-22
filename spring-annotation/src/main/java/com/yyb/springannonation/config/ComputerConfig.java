package com.yyb.springannonation.config;

import com.yyb.springannonation.model.Computer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ComputerConfig {
    @Bean
    public Computer computer() {
        return new Computer("macbook");
    }
}
