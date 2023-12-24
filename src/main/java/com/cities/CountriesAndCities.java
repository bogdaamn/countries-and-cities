package com.cities;

import com.cities.config.property.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class CountriesAndCities {

    public static void main(String[] args) {
        SpringApplication.run(CountriesAndCities.class, args);
    }
}