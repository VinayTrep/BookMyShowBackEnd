package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Auditorium;

import java.util.UUID;

public record ShowAuditoriumResponseDto(UUID id, String auditoriumName) {

    public static ShowAuditoriumResponseDto fromAuditorium(Auditorium auditorium) {
        return new ShowAuditoriumResponseDto(auditorium.getId(),auditorium.getAuditoriumName());
    }
}
