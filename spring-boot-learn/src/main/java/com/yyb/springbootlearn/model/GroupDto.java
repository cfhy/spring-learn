package com.yyb.springbootlearn.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:custom.properties")
@ConfigurationProperties(prefix = "group")
@Data
public class GroupDto {
    private List<UserDto> users;
}
