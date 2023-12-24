package com.cities.persistance.repository;

import com.cities.persistance.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    void deleteByName(String cityName);

    List<CityEntity> findAllByCountryId(Long id);
}
