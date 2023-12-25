package com.cities.persistance.repository;

import com.cities.persistance.entity.CityEntity;
import com.cities.rest.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    void deleteByName(String cityName);

    List<CityEntity> findAllByCountryId(Long id);

    @Query("""
            select city
              from CityEntity city
             inner join CountryEntity country
                on city.countryId = country.id
             where country.name = :countryName
            """)
    List<CityEntity> getAllCitiesByCountryName(String countryName);

}
