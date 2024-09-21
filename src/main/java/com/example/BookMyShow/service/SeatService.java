package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CreateSeatRequestDto;
import com.example.BookMyShow.dto.SeatResponseDto;
import com.example.BookMyShow.dto.UpdateSeatRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.SeatNotFoundException;

import java.util.List;
import java.util.UUID;

public interface SeatService {

    SeatResponseDto addSeat(CreateSeatRequestDto requestDto);
    List<SeatResponseDto> addSeats(List<CreateSeatRequestDto> requestDTOs);
    SeatResponseDto getSeat(UUID seatId) throws SeatNotFoundException;
    List<SeatResponseDto> getAllSeats(UUID auditoriumId) throws AuditoriumNotFoundException;
    SeatResponseDto updateSeat(UUID seatId,UpdateSeatRequestDto requestDto) throws AuditoriumNotFoundException, SeatNotFoundException;
    void deleteSeat(UUID seatId);

}
