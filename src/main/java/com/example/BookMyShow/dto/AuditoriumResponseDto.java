package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;

import java.util.List;
import java.util.UUID;

public record AuditoriumResponseDto(UUID id, String auditoriumName,int capacity,List<ShowResponseDto> shows,
                                    List<SeatResponseDto> seats,
                                    List<AuditoriumFeatures> auditoriumFeatures) {
    public static AuditoriumResponseDto fromAuditorium(Auditorium auditorium) {
        List<ShowResponseDto> shows = auditorium.getShows().stream().map(ShowResponseDto::fromShow).toList();
        List<SeatResponseDto> seats = auditorium.getSeats().stream().map(SeatResponseDto::fromSeat).toList();
        return new AuditoriumResponseDto(auditorium.getId(),auditorium.getAuditoriumName(),auditorium.getCapacity()
        ,shows,seats,auditorium.getAuditoriumFeatures());
    }
}
