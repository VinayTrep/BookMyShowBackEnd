package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CreateTheaterRequestDto;
import com.example.BookMyShow.dto.TheaterResponseDto;
import com.example.BookMyShow.dto.UpdateTheaterRequestDto;
import com.example.BookMyShow.exception.CityNotFoundException;
import com.example.BookMyShow.exception.TheaterNotFoundException;
import com.example.BookMyShow.model.City;
import com.example.BookMyShow.model.Theater;
import com.example.BookMyShow.repository.CityRepository;
import com.example.BookMyShow.repository.TheaterRepository;
import com.example.BookMyShow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("TheaterServiceImpl")
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final CityRepository cityRepository;
    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository ,CityRepository cityRepository) {
        this.theaterRepository = theaterRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public TheaterResponseDto addTheater(CreateTheaterRequestDto theaterRequestDto) throws CityNotFoundException {
        City city = cityRepository.findById(theaterRequestDto.cityId()).orElseThrow(()-> new CityNotFoundException("City not found"));
        Theater theater = CreateTheaterRequestDto.toTheater(theaterRequestDto);
        List<Theater> theaters = city.getTheaters() == null ? new ArrayList<>() : city.getTheaters();
        theaters.add(theater);
        city.setTheaters(theaters);
        theater.setCity(city);

        return TheaterResponseDto.fromTheater(theaterRepository.save(theater));
    }

    @Override
    public TheaterResponseDto updateTheater(UUID id, UpdateTheaterRequestDto theaterRequestDto) throws TheaterNotFoundException, CityNotFoundException {
        City city = cityRepository.findById(theaterRequestDto.cityId()).orElseThrow(()-> new CityNotFoundException("City not found"));
        Theater theater = theaterRepository.findById(id).orElseThrow(()-> new TheaterNotFoundException("Theater not found"));

        theater.setCity(city);
        theater.setTheaterName(theaterRequestDto.theaterName());
        theater.setTheaterAddress(theaterRequestDto.theaterAddress());
        theater.setTheaterImageUrl(theaterRequestDto.theaterImageUrl());

        return TheaterResponseDto.fromTheater(theaterRepository.save(theater));
    }

    @Override
    public void deleteTheater(UUID id) {
        theaterRepository.deleteById(id);
    }

    @Override
    public TheaterResponseDto getTheater(UUID id) throws TheaterNotFoundException {
        Theater theater = theaterRepository.findById(id).orElseThrow(()-> new TheaterNotFoundException("Theater not found"));

        return TheaterResponseDto.fromTheater(theater);
    }

    @Override
    public List<TheaterResponseDto> getAllTheaters() {
        return theaterRepository.findAll().stream().map(TheaterResponseDto::fromTheater).toList();
    }

}
