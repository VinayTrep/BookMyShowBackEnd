package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.model.constants.MovieFeatures;
import com.example.BookMyShow.model.constants.MovieGenre;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record CreateMovieRequestDto(String title, String genre, int year, String director, int duration,
                                    List<String> movieFeatures,
                                    List<UUID> actorIds) {

    public static Movie fromDto(CreateMovieRequestDto createMovieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(createMovieRequestDto.title);
        movie.setGenre(MovieGenre.valueOf(createMovieRequestDto.genre));
        movie.setYear(createMovieRequestDto.year);
        movie.setDirector(createMovieRequestDto.director);
        movie.setDuration(createMovieRequestDto.duration);

        List<MovieFeatures> movieFeatures = createMovieRequestDto.movieFeatures.stream().map(MovieFeatures::valueOf).toList();
        movie.setMovieFeatures(movieFeatures);
        movie.setActors(new ArrayList<>());
        return movie;
    }
}
