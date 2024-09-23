package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.CreateSeatRequestDto;
import com.example.BookMyShow.dto.SeatResponseDto;
import com.example.BookMyShow.dto.UpdateSeatRequestDto;
import com.example.BookMyShow.exception.InvalidSeatStatusException;
import com.example.BookMyShow.exception.InvalidSeatTypeException;
import com.example.BookMyShow.model.constants.SeatStatus;
import com.example.BookMyShow.model.constants.SeatType;
import com.example.BookMyShow.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;
    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/")

    public ResponseEntity<SeatResponseDto> addSeat(@RequestBody CreateSeatRequestDto seatRequestDto) {
        validateSeatStatusAndType(seatRequestDto.seatStatus(),seatRequestDto.SeatType(),seatRequestDto.SeatNumber());
        return ResponseEntity.ok(seatService.addSeat(seatRequestDto));
    }
    @PostMapping("/addall")
    public ResponseEntity<List<SeatResponseDto>> addSeats(@RequestBody List<CreateSeatRequestDto> seatRequestDtos) {
        for (CreateSeatRequestDto seatRequestDto : seatRequestDtos) {
            validateSeatStatusAndType(seatRequestDto.seatStatus(),seatRequestDto.SeatType(),seatRequestDto.SeatNumber());
        }
        return ResponseEntity.ok(seatService.addSeats(seatRequestDtos));
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<SeatResponseDto> getSeatById(@PathVariable("seatId")UUID seatId) {
        return ResponseEntity.ok(seatService.getSeat(seatId));
    }

    @GetMapping("/getall/{auditoriumId}")
    public ResponseEntity<List<SeatResponseDto>> getAllSeatsByAuditoriumId(@PathVariable("auditoriumId")UUID auditoriumId) {
        return ResponseEntity.ok(seatService.getAllSeats(auditoriumId));
    }

    @PutMapping("/{seatId}")
    public ResponseEntity<SeatResponseDto> updateSeat(@PathVariable("seatId") UUID seatId, @RequestBody UpdateSeatRequestDto requestDto) {
        validateSeatStatusAndType(requestDto.seatStatus(),requestDto.SeatType(),requestDto.SeatNumber());
        return ResponseEntity.ok(seatService.updateSeat(seatId, requestDto));
    }

    @DeleteMapping("/{seatID}")
    public ResponseEntity<String> deleteSeat(@PathVariable("seatID") UUID seatID) {
        seatService.deleteSeat(seatID);
        return ResponseEntity.ok("Seat deleted Successfully");
    }

    private void validateSeatStatusAndType(String seatStatus, String seatType, String seatNumber) {

        try {
            SeatType.valueOf(seatType);
        }catch (IllegalArgumentException e) {
            throw new InvalidSeatTypeException("Seat type is not valid for " + seatNumber);
        }

        try {
            SeatStatus.valueOf(seatStatus);
        }catch (IllegalArgumentException e) {
            throw new InvalidSeatStatusException("Seat Status is not valid " + seatNumber);
        }
    }

}
