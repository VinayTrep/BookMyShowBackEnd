package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Actor;
import com.example.BookMyShow.model.Movie;

import java.util.UUID;

public record ActorMovieResponseDto(UUID movieId, String title, String genre,
                                    int year, String director) {

    public static ActorMovieResponseDto fromEntity(Movie movie){
        return new ActorMovieResponseDto(movie.getId(), movie.getTitle(), movie.getGenre().name(),
                movie.getYear(), movie.getDirector());
    }
}
