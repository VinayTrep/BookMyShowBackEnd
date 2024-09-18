package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Movie;

import java.util.List;
import java.util.UUID;

public record MovieResponseDto(UUID movieId, String title, String genre,
                               int year, String director, int duration,
                               List<String> movieFeatures,
                               List<MovieActorResponseDto> actors) {


    public static MovieResponseDto fromEntity(Movie movie) {
        return new MovieResponseDto(movie.getId(), movie.getTitle(), movie.getGenre().name(),
                movie.getYear(),movie.getDirector(), movie.getDuration(),
                movie.getMovieFeatures().stream().map(Enum::name).toList(),
                movie.getActors().stream().map(MovieActorResponseDto::fromActor).toList());
    }
}
