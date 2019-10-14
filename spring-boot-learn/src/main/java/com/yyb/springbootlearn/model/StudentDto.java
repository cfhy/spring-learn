package com.yyb.springbootlearn.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:custom.properties")
@ConfigurationProperties(prefix = "student")
@Data
public class StudentDto {
    String name;
    Integer age;
}
