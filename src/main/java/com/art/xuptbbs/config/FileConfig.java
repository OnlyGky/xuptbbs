package com.art.xuptbbs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application-dev.yml")
@ConfigurationProperties(prefix = "image")
@Configuration
public class FileConfig {

    @Value("${avatarUploadFolder}")
    private String avatarUploadFolder;

    @Value("${postUploadFolder}")
    private String postUploadFolder;

    @Value("${avatarImagePath}")
    private String avatarImagePath;

    @Value("${postImagePath}")
    private String postImagePath;
}
