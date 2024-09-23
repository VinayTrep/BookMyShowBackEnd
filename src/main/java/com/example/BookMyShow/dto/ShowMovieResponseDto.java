package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Movie;

import java.io.Serializable;
import java.util.UUID;

public record ShowMovieResponseDto(UUID movieID, String title, String genre, int year,
                                   String director, int duration) implements Serializable {

    public static ShowMovieResponseDto from(Movie movie) {
        return new ShowMovieResponseDto(movie.getId(), movie.getTitle(), movie.getGenre().name(), movie.getYear(),movie.getDirector(), movie.getDuration());
    }
}
