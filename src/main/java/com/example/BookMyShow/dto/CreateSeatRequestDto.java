package com.example.BookMyShow.dto;


import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.constants.SeatStatus;

import java.util.UUID;

public record CreateSeatRequestDto(String SeatNumber, int columnNumber, int rowsNumber, String SeatType, String seatStatus,
                                   UUID auditoriumId) {

    public static Seat fromDto(CreateSeatRequestDto createSeatRequestDto) {
        Seat seat = new Seat();
        seat.setSeatNumber(createSeatRequestDto.SeatNumber);
        seat.setColumnNumber(createSeatRequestDto.columnNumber);
        seat.setRowsNumber(createSeatRequestDto.rowsNumber);
        seat.setSeatStatus(SeatStatus.valueOf(createSeatRequestDto.seatStatus));
        seat.setSeatType(com.example.BookMyShow.model.constants.SeatType.valueOf(createSeatRequestDto.SeatType));
        return seat;
    }
}