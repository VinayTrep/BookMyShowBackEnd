package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CitiesResponseDto;
import com.example.BookMyShow.dto.CityResponseDto;
import com.example.BookMyShow.dto.CreateCityRequestDto;
import com.example.BookMyShow.dto.UpdateCityRequestDto;
import com.example.BookMyShow.exception.CityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CityService {

     CityResponseDto addCity(CreateCityRequestDto requestDto);
     CityResponseDto getCityById(UUID id) throws CityNotFoundException;
     List<CitiesResponseDto> getAllCities();
     CityResponseDto updateCity(UUID id, UpdateCityRequestDto requestDto) throws CityNotFoundException;
     void deleteCity(UUID id);
}
