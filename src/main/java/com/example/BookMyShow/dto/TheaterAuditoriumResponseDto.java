package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;

import java.util.List;
import java.util.UUID;

public record TheaterAuditoriumResponseDto(UUID id, String auditoriumName, int capacity, List<ShowResponseDto> shows,
                                           List<AuditoriumFeatures> auditoriumFeatures) {
    public static TheaterAuditoriumResponseDto fromAuditorium(Auditorium auditorium) {
        List<ShowResponseDto> shows = auditorium.getShows().stream().map(ShowResponseDto::fromShow).toList();
        return new TheaterAuditoriumResponseDto(auditorium.getId(),auditorium.getAuditoriumName(),auditorium.getCapacity()
                ,shows,auditorium.getAuditoriumFeatures());
    }
}
