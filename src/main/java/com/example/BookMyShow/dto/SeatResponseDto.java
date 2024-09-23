package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Seat;

import java.io.Serializable;
import java.util.UUID;

public record SeatResponseDto(UUID seatId,String SeatNumber, int columnNumber,
                              int rowsNumber, String SeatType,
                              String seatStatus) implements Serializable {

    public static SeatResponseDto fromSeat(Seat seat) {
        return new SeatResponseDto(seat.getId(),seat.getSeatNumber(),seat.getColumnNumber(), seat.getRowsNumber(), seat.getSeatType().name(),seat.getSeatStatus().name());
    }
}
