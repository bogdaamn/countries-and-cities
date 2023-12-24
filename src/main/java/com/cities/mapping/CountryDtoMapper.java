package com.cities.mapping;

import com.cities.persistance.entity.CountryEntity;
import com.cities.rest.dto.CityDto;
import com.cities.rest.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CountryDtoMapper {

    public abstract CountryDto map(CountryEntity countryEntity, List<CityDto> cities);

    public abstract List<CountryDto> map(List<CountryEntity> source);
}
