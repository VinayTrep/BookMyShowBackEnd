package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.City;

import java.util.List;
import java.util.UUID;

public record CityResponseDto(UUID id,String name, List<TheaterResponseDto> theaters) {

    public static CityResponseDto fromCity(City city) {
        List<TheaterResponseDto> theaters = city.getTheaters().stream().map(TheaterResponseDto::fromTheater).toList();
        return new CityResponseDto(city.getId(),city.getName(), theaters);
    }
}
