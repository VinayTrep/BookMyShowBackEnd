package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Movie;

import java.util.UUID;

public record ShowMovieResponseDto(UUID movieID, String title, String genre, int year,
                                   String director, int duration) {

    public static ShowMovieResponseDto from(Movie movie) {
        return new ShowMovieResponseDto(movie.getId(), movie.getTitle(), movie.getGenre().name(), movie.getYear(),movie.getDirector(), movie.getDuration());
    }
}
