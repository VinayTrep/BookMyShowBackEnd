package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;

import java.io.Serializable;

public record TicketShowResponseDto(String startTime, String endTime, String movieName) implements Serializable {

    public static TicketShowResponseDto toDto(Show show) {
        return new TicketShowResponseDto(show.getStartTime().toString(),show.getEndTime().toString(),show.getMovie().getTitle());
    }
}
