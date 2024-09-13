package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.CitiesResponseDto;
import com.example.BookMyShow.dto.CityResponseDto;
import com.example.BookMyShow.dto.CreateCityRequestDto;
import com.example.BookMyShow.dto.UpdateCityRequestDto;
import com.example.BookMyShow.exception.CityNameEmptyException;
import com.example.BookMyShow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/")
    public ResponseEntity<CityResponseDto> addCity(@RequestBody CreateCityRequestDto requestDto){
        validateCreateCity(requestDto);
        CityResponseDto cityResponseDto = cityService.addCity(requestDto);
        return ResponseEntity.ok(cityResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDto> getCityById(@PathVariable("id") UUID id){
        CityResponseDto cityResponseDto = cityService.getCityById(id);
        return ResponseEntity.ok(cityResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<CitiesResponseDto>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDto> updateCity(@PathVariable("id") UUID id, @RequestBody UpdateCityRequestDto requestDto){
        validateUpdateCity(requestDto);
        CityResponseDto cityResponseDto = cityService.updateCity(id, requestDto);
        return ResponseEntity.ok(cityResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") UUID id){
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.OK).body("City deleted Successfully");
    }

    private void validateCreateCity(CreateCityRequestDto requestDto){
        String cityName = requestDto.name();

        if(cityName.isEmpty() || cityName.isBlank()){
            throw new CityNameEmptyException("City name cannot be empty");
        }
    }
    private void validateUpdateCity(UpdateCityRequestDto requestDto){
        String cityName = requestDto.name();

        if(cityName.isEmpty() || cityName.isBlank()){
            throw new CityNameEmptyException("City name cannot be empty");
        }
    }
}
