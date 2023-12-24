package com.cities.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "app")
public class LogoProperties {

    private final String logoDirectoryPath;

    @ConstructorBinding
    public LogoProperties(String logoDirectoryPath) {
        this.logoDirectoryPath = logoDirectoryPath;
    }

    public String getLogoDirectoryPath() {
        return logoDirectoryPath;
    }
}
