package com.cities.persistance.repository;

import com.cities.persistance.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByName(String countryName);
}
