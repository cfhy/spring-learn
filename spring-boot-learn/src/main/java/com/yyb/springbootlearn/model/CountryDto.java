package com.yyb.springbootlearn.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:custom.properties")
@ConfigurationProperties(prefix = "country")
@Data
public class CountryDto {
    private List<String> cities;
}
