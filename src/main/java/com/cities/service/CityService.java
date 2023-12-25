package com.cities.service;

import com.cities.exception.CityNotFoundException;
import com.cities.exception.ExtensionNotAllowedException;
import com.cities.exception.FileNotUploadedException;
import com.cities.mapping.CityDtoMapper;
import com.cities.mapping.CityEntityMapper;
import com.cities.persistance.entity.CityEntity;
import com.cities.persistance.repository.CityRepository;
import com.cities.rest.dto.CityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import static com.cities.utils.Constants.ALLOWED_EXTENSIONS;
import static com.cities.utils.Constants.LOGO_DIRECTORY_PATH;

@Service
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private final CityEntityMapper cityEntityMapper;
    private final CityRepository cityRepository;
    private final CityDtoMapper cityDtoMapper;

    public CityService(CityEntityMapper cityEntityMapper, CityRepository cityRepository, CityDtoMapper cityDtoMapper) {
        this.cityEntityMapper = cityEntityMapper;
        this.cityRepository = cityRepository;
        this.cityDtoMapper = cityDtoMapper;
    }

    public CityDto getCityByName(Long id) {
        CityEntity cityEntity = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("No city with id %s were found".formatted(id)));

        return cityDtoMapper.map(cityEntity);
    }


    @Transactional
    @Modifying
    public void deleteCity(String cityName) {
        cityRepository.deleteByName(cityName);
    }

    public List<CityDto> findAllByCountryId(Long id) {
        return cityDtoMapper.map(cityRepository.findAllByCountryId(id));
    }

    public void addCity(String cityName, MultipartFile file) {
        checkExtension(file);

        storeFile(file);
        CityEntity cityEntity = createCityEntity(cityName, file.getOriginalFilename());
        cityRepository.save(cityEntity);

        log.debug("City with name {} has been successfully added to the database", cityName);
    }

    private void checkExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new ExtensionNotAllowedException("Invalid file extension. Allowed extensions: " + ALLOWED_EXTENSIONS);
        }
    }

    private CityEntity createCityEntity(String cityName, String logoName) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);
        cityEntity.setLogoName(logoName);
        return cityEntity;
    }

    private void storeFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        try {
            Path directory = Path.of(LOGO_DIRECTORY_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Path filePath = directory.resolve(Objects.requireNonNull(originalFileName));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new FileNotUploadedException("Failed to store file " + originalFileName);
        }
    }

    public List<CityDto> getAllCitiesByCountryName(String countryName) {
        var cityEntities = cityRepository.getAllCitiesByCountryName(countryName);
        return cityDtoMapper.map(cityEntities);
    }

    public Page<CityDto> getAllCities(PageRequest pageRequest) {
        var cities = cityRepository.findAll(pageRequest);
        return cities.map(cityDtoMapper::map);
    }

//    private byte[] readImageAsByteArray(String imagePath) {
//        try (InputStream in = getClass().getResourceAsStream(imagePath)) {
//            if (in == null) {
//                throw new FileNotFoundException("Image file not found at " + imagePath);
//            }
//            return in.readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException("Error reading image file", e);
//        }
//    }
    //        String imagePath = "/logos/" + cityEntity.getLogoName();
//        byte[] image = readImageAsByteArray(imagePath);
//        String base64Image = Base64.getEncoder().encodeToString(image);
//        ByteArrayInputStream bis = new ByteArrayInputStream(image);
//        try {
//            BufferedImage bufferedImage = ImageIO.read(bis);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        cityDto.setFileUrl(base64Image);
}
