package com.cities.mapping;

import com.cities.persistance.entity.CityEntity;
import com.cities.rest.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CityEntityMapper {

    public abstract CityEntity map(CityDto source);
}
