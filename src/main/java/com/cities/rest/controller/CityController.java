package com.cities.rest.controller;

import com.cities.rest.dto.CityDto;
import com.cities.service.CityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "City controller", description = "API for managing cities")
@RestController
@RequestMapping("/${api.version}/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('USER')")
    public Page<CityDto> getAllCities(@RequestParam Integer pageNumber,
                                      @RequestParam Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return cityService.getAllCities(pageRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<CityDto> getCitiesByCountryName(@RequestParam String countryName) {
        return cityService.getAllCitiesByCountryName(countryName);
    }

    @GetMapping(path = "/{id}")
    public CityDto getCityByName(@PathVariable Long id) {
        return cityService.getCityByName(id);
    }

    @PostMapping(path = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<?> addCity(@RequestParam String cityName,
                                     @RequestParam("logo") MultipartFile file) {
        cityService.addCity(cityName, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deleteCity(@RequestParam String cityName) {
        try {
            cityService.deleteCity(cityName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
