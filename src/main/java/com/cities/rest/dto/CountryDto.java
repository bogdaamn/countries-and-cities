package com.cities.rest.dto;

import java.util.List;
import java.util.Objects;

public class CountryDto {

    private String name;
    private List<CityDto> cities;


    public CountryDto(String name, List<CityDto> cities) {
        this.name = name;
        this.cities = cities;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDto that = (CountryDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
