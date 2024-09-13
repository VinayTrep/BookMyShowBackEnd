package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CreateTheaterRequestDto;
import com.example.BookMyShow.dto.TheaterResponseDto;
import com.example.BookMyShow.dto.UpdateTheaterRequestDto;
import com.example.BookMyShow.exception.CityNotFoundException;
import com.example.BookMyShow.exception.TheaterNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TheaterService {

    public TheaterResponseDto addTheater(CreateTheaterRequestDto theaterRequestDto) throws CityNotFoundException;
    public TheaterResponseDto updateTheater(UUID id,UpdateTheaterRequestDto theaterRequestDto) throws TheaterNotFoundException, CityNotFoundException;
    public void deleteTheater(UUID id);
    public TheaterResponseDto getTheater(UUID id) throws  TheaterNotFoundException;
    List<TheaterResponseDto> getAllTheaters();
}
