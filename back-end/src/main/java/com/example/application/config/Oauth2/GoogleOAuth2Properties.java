package com.example.application.config.Oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "google")
public class GoogleOAuth2Properties {
    private String clientId;
    private String clientSecret;
}
