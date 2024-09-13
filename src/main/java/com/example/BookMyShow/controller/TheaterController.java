package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.CreateTheaterRequestDto;
import com.example.BookMyShow.dto.TheaterResponseDto;
import com.example.BookMyShow.dto.UpdateTheaterRequestDto;
import com.example.BookMyShow.exception.TheaterAddressEmptyException;
import com.example.BookMyShow.exception.TheaterImageEmptyException;
import com.example.BookMyShow.exception.TheaterNameEmptyException;
import com.example.BookMyShow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/")
    public ResponseEntity<TheaterResponseDto> createTheater(@RequestBody CreateTheaterRequestDto createTheaterRequestDto) {
        validateTheaterRequestDto(createTheaterRequestDto.theaterName(),createTheaterRequestDto.theaterAddress(),createTheaterRequestDto.theaterImageUrl());
        TheaterResponseDto responseDto = theaterService.addTheater(createTheaterRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<TheaterResponseDto> getTheaterById(@PathVariable UUID theaterId) {
        TheaterResponseDto responseDto = theaterService.getTheater(theaterId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @GetMapping("/")
    public ResponseEntity<List<TheaterResponseDto>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @PutMapping("/{theaterId}")
    public ResponseEntity<TheaterResponseDto> updateTheater(@PathVariable UUID theaterId, @RequestBody UpdateTheaterRequestDto updateTheaterRequestDto) {
        validateTheaterRequestDto(updateTheaterRequestDto.theaterName(),updateTheaterRequestDto.theaterAddress(),updateTheaterRequestDto.theaterImageUrl());
        TheaterResponseDto responseDto = theaterService.updateTheater(theaterId, updateTheaterRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @DeleteMapping("/{theaterId}")
    public ResponseEntity<String> deleteTheater(@PathVariable UUID theaterId) {
        theaterService.deleteTheater(theaterId);
        return ResponseEntity.ok("Deleted theater with id " + theaterId);
    }

    private void validateTheaterRequestDto(String theaterName,String theaterAddress,String theaterImageUrl) {
        if(theaterName.isBlank() || theaterName.isEmpty()){
            throw new TheaterNameEmptyException("Theater name cannot be empty exception");
        }

        if(theaterAddress.isEmpty() || theaterAddress.isBlank()){
            throw new TheaterAddressEmptyException("Theater address cannot be empty exception");
        }
        if (theaterImageUrl.isEmpty() || theaterImageUrl.isBlank()){
            throw new TheaterImageEmptyException("Theater image cannot be empty exception");
        }
    }
}
