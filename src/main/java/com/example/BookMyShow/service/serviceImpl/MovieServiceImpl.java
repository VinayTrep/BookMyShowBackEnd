package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CreateMovieRequestDto;
import com.example.BookMyShow.dto.MovieResponseDto;
import com.example.BookMyShow.dto.UpdateMovieRequestDto;
import com.example.BookMyShow.exception.MovieNotFoundException;
import com.example.BookMyShow.model.Actor;
import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.model.constants.MovieFeatures;
import com.example.BookMyShow.model.constants.MovieGenre;
import com.example.BookMyShow.repository.ActorRepository;
import com.example.BookMyShow.repository.MovieRepository;
import com.example.BookMyShow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("MovieServiceImpl")
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public MovieResponseDto createMovie(CreateMovieRequestDto createMovieRequestDto) {
        Movie movie = CreateMovieRequestDto.fromDto(createMovieRequestDto);
        if (!createMovieRequestDto.actorIds().isEmpty()){
            List<Actor> actors = actorRepository.findAllByIdIn(createMovieRequestDto.actorIds());
            for (Actor actor : actors) {
                List<Movie> movieList = actor.getMovies() != null ? actor.getMovies() : new ArrayList<>();
                movieList.add(movie);
                actor.setMovies(movieList);
            }
            movie.setActors(actors);
        }
        return MovieResponseDto.fromEntity(movieRepository.save(movie));
    }

    @Override
    public MovieResponseDto updateMovie(UUID movieID, UpdateMovieRequestDto updateMovieRequestDto) throws MovieNotFoundException {

        Movie movie = movieRepository.findById(movieID).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        movie.setGenre(MovieGenre.valueOf(updateMovieRequestDto.genre()));
        movie.setDirector(updateMovieRequestDto.director());
        movie.setTitle(updateMovieRequestDto.title());
        movie.setYear(updateMovieRequestDto.year());
        movie.setDuration(updateMovieRequestDto.duration());
        List<MovieFeatures> movieFeatures = updateMovieRequestDto.movieFeatures()
                .stream()
                .map(MovieFeatures::valueOf)
                .collect(Collectors.toList());
        movie.setMovieFeatures(movieFeatures);

        if (!updateMovieRequestDto.actorIds().isEmpty()){
            List<Actor> actors = actorRepository.findAllByIdIn(updateMovieRequestDto.actorIds());
            for (Actor actor : actors) {
                List<Movie> movieList = actor.getMovies() != null ? actor.getMovies() : new ArrayList<>();
                if(!movieList.contains(movie)){
                    movieList.add(movie);
                }
                actor.setMovies(movieList);
            }
//            removes the movie from actor_movies when an actor is removed from the movies
            for (Actor actor : movie.getActors()) {
                if(!actors.contains(actor)){
                    actor.getMovies().remove(movie);
                }
            }
            movie.setActors(actors);
        }else{
            List<Actor> actors = movie.getActors();
            for (Actor actor : actors) {
                actor.getMovies().remove(movie);
                List<Movie> movieList = actor.getMovies() != null ? actor.getMovies() : new ArrayList<>();
                movieList.remove(movie);
                actor.setMovies(movieList);
            }
            movie.setActors(new ArrayList<>());
        }

        return MovieResponseDto.fromEntity(movieRepository.save(movie));
    }


    @Override
    public MovieResponseDto getMovieById(UUID movieId) throws MovieNotFoundException{
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        return MovieResponseDto.fromEntity(movie);
    }

    @Override
    public void deleteMovie(UUID movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            List<Actor> actors = movie.getActors();
            for (Actor actor : actors) {
                actor.getMovies().remove(movie);
            }
        }
        movieRepository.deleteById(movieId);
    }
}
