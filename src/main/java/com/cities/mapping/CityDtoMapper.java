package com.cities.mapping;

import com.cities.persistance.entity.CityEntity;
import com.cities.rest.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CityDtoMapper {

    @Mapping(source = "name", target = "name")
    public abstract CityDto map(CityEntity source);

    public abstract List<CityDto> map(List<CityEntity> source);

}
