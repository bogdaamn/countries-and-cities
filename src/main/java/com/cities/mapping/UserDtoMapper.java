package com.cities.mapping;

import com.cities.persistance.entity.UserEntity;
import com.cities.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserDtoMapper {

    public abstract UserDto map(UserEntity source);
}