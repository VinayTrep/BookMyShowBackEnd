package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CitiesResponseDto;
import com.example.BookMyShow.dto.CityResponseDto;
import com.example.BookMyShow.dto.CreateCityRequestDto;
import com.example.BookMyShow.dto.UpdateCityRequestDto;
import com.example.BookMyShow.exception.CityNotFoundException;
import com.example.BookMyShow.model.City;
import com.example.BookMyShow.repository.CityRepository;
import com.example.BookMyShow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("CityServiceImpl")
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponseDto addCity(CreateCityRequestDto requestDto) {
        City city = CreateCityRequestDto.fromCreateCityRequestDto(requestDto);
        return CityResponseDto.fromCity(cityRepository.save(city));
    }

    @Override
    public CityResponseDto getCityById(UUID id) throws CityNotFoundException {
        City city = cityRepository.findById(id).orElseThrow( () -> new  CityNotFoundException("City with given id does not exist"));
        return CityResponseDto.fromCity(city);
    }

    @Override
    public List<CitiesResponseDto> getAllCities() {
        return cityRepository.findAll().stream().map(CitiesResponseDto::fromCity).toList();
    }

    @Override
    public CityResponseDto updateCity(UUID id, UpdateCityRequestDto requestDto) throws CityNotFoundException {
        City city = cityRepository.findById(id).orElseThrow(() -> new  CityNotFoundException("City with given id does not exist"));
        city.setName(requestDto.name());
        return CityResponseDto.fromCity(cityRepository.save(city));
    }

    @Override
    public void deleteCity(UUID id) {
        cityRepository.deleteById(id);
    }
}
