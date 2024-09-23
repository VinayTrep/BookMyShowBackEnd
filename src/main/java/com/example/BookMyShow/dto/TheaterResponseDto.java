package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record TheaterResponseDto(UUID id,String theaterName,
                                 String theaterAddress,
                                 String theaterImageUrl,
                                 List<TheaterAuditoriumResponseDto> auditoriums) implements Serializable {

    public static TheaterResponseDto fromTheater(Theater theater) {
        List<TheaterAuditoriumResponseDto> auditoriums = theater.getAuditoriums() == null ? new ArrayList<>() : theater.getAuditoriums().stream().map(TheaterAuditoriumResponseDto::fromAuditorium).toList();
        return new TheaterResponseDto(theater.getId(),theater.getTheaterName(), theater.getTheaterAddress(), theater.getTheaterImageUrl(),auditoriums);
    }
}
