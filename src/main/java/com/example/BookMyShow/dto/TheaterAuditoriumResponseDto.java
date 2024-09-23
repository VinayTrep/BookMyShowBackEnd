package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record TheaterAuditoriumResponseDto(UUID id, String auditoriumName, int capacity, List<BMSShowsResponseDto> shows,
                                           List<AuditoriumFeatures> auditoriumFeatures) implements Serializable {
    public static TheaterAuditoriumResponseDto fromAuditorium(Auditorium auditorium) {
        List<BMSShowsResponseDto> shows = auditorium.getShows().stream().map(BMSShowsResponseDto::fromShow).toList();
        return new TheaterAuditoriumResponseDto(auditorium.getId(),auditorium.getAuditoriumName(),auditorium.getCapacity()
                ,shows,auditorium.getAuditoriumFeatures());
    }
}
