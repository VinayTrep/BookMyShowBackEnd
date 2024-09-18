package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.CreateMovieRequestDto;
import com.example.BookMyShow.dto.MovieResponseDto;
import com.example.BookMyShow.dto.UpdateMovieRequestDto;
import com.example.BookMyShow.exception.InvalidMovieFeatureException;
import com.example.BookMyShow.model.constants.MovieFeatures;
import com.example.BookMyShow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody CreateMovieRequestDto requestDto) {
        validateMovieFeatures(requestDto.movieFeatures());
        return ResponseEntity.ok(movieService.createMovie(requestDto));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @PutMapping("/{movieID}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable UUID movieID, @RequestBody UpdateMovieRequestDto requestDto) {
        validateMovieFeatures(requestDto.movieFeatures());
        return ResponseEntity.ok(movieService.updateMovie(movieID, requestDto));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable UUID movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted Successfully");
    }

    private void validateMovieFeatures(List<String> movieFeatures){
        try {
            for (String feature : movieFeatures) {
                MovieFeatures.valueOf(feature);
            }
        }catch (IllegalArgumentException e){
            throw new InvalidMovieFeatureException("Invalid movie feature ");
        }
    }

}
