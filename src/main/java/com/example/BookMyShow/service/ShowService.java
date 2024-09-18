package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.BMSShowResponseDto;
import com.example.BookMyShow.dto.BMSShowsResponseDto;
import com.example.BookMyShow.dto.CreateShowRequestDto;
import com.example.BookMyShow.dto.UpdateShowRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.MovieNotFoundException;
import com.example.BookMyShow.exception.ShowNotFoundException;
import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.ShowSeat;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ShowService {

    BMSShowResponseDto createShow(CreateShowRequestDto createShowRequestDto) throws AuditoriumNotFoundException, MovieNotFoundException;
    BMSShowResponseDto getShow(UUID showId) throws ShowNotFoundException;
    List<BMSShowsResponseDto> getAllShow(UUID auditorId) throws ShowNotFoundException;
    BMSShowResponseDto updateShow(UpdateShowRequestDto updateShowRequestDto);
    void deleteShow(UUID showId);
    List<ShowSeat> generateShowSeats(Show show,List<Seat> showSeats);
}
