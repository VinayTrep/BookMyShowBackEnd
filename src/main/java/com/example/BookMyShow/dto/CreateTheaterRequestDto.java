package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Theater;

import java.util.UUID;

public record CreateTheaterRequestDto(String theaterName, String theaterAddress, String theaterImageUrl, UUID cityId) {

    public static Theater toTheater(CreateTheaterRequestDto createTheaterRequestDto) {
        Theater theater = new Theater();
        theater.setTheaterName(createTheaterRequestDto.theaterName);
        theater.setTheaterAddress(createTheaterRequestDto.theaterAddress);
        theater.setTheaterImageUrl(createTheaterRequestDto.theaterImageUrl);
        return theater;
    }
}
