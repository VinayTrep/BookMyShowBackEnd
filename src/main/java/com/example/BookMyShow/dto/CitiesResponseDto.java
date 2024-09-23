package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.City;

import java.io.Serializable;
import java.util.UUID;

public record CitiesResponseDto(UUID id, String name) implements Serializable {

    public static CitiesResponseDto fromCity(City city) {
        return new CitiesResponseDto(city.getId(),city.getName());
    }
}
