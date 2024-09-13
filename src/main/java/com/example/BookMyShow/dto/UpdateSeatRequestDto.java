package com.example.BookMyShow.dto;


import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.constants.SeatStatus;

import java.util.UUID;

public record UpdateSeatRequestDto(String SeatNumber, int columnNumber, int rowsNumber, String SeatType, String seatStatus,
                                   UUID auditoriumId) {
}