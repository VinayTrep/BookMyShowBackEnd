package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.City;

import java.util.ArrayList;

public record CreateCityRequestDto(String name) {

    public static City fromCreateCityRequestDto(CreateCityRequestDto requestDto){
        City city = new City();
        city.setName(requestDto.name);
        city.setTheaters(new ArrayList<>());
        return city;
    }
}
