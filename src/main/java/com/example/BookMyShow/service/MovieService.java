package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CreateMovieRequestDto;
import com.example.BookMyShow.dto.MovieResponseDto;
import com.example.BookMyShow.dto.UpdateMovieRequestDto;
import com.example.BookMyShow.exception.MovieNotFoundException;

import java.util.UUID;

public interface MovieService {

    MovieResponseDto createMovie(CreateMovieRequestDto createMovieRequestDto);
    MovieResponseDto updateMovie(UUID movieID,UpdateMovieRequestDto updateMovieRequestDto) throws MovieNotFoundException;
    MovieResponseDto getMovieById(UUID movieId) throws MovieNotFoundException;
    void deleteMovie(UUID movieId);
}
