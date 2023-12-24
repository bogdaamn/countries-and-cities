package com.cities.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import static java.util.Objects.requireNonNull;

@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private final String version;

    @ConstructorBinding
    public ApiProperties(String version) {
        this.version = requireNonNull(version, "api version must be configured");
    }

    public String getVersion() {
        return version;
    }


}
