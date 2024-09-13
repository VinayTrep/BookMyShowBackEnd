package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Seat;

public record SeatResponseDto() {

    public static SeatResponseDto fromSeat(Seat seat) {
        return new SeatResponseDto();
    }
}
