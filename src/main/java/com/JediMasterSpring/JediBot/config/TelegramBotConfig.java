package com.JediMasterSpring.JediBot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import org.hibernate.validator.constraints.NotBlank;


@ConfigurationProperties(prefix = "telegram")
@Validated
public class TelegramBotConfig {

    @NotBlank
    private String token;

    @NotBlank
    private String username;

    @NotBlank
    private String path;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
