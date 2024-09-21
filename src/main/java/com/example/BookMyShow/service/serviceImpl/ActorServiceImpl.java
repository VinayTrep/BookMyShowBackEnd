package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.ActorResponseDto;
import com.example.BookMyShow.dto.CreateActorRequestDto;
import com.example.BookMyShow.dto.UpdateActorRequestDto;
import com.example.BookMyShow.exception.ActorNotFoundException;
import com.example.BookMyShow.model.Actor;
import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.repository.ActorRepository;
import com.example.BookMyShow.repository.MovieRepository;
import com.example.BookMyShow.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("ActorServiceImpl")
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public ActorResponseDto createActor(CreateActorRequestDto requestDto) {
        Actor actor = CreateActorRequestDto.fromDto(requestDto);
        if(!requestDto.movieIds().isEmpty()){
            List<Movie> movieList = movieRepository.findAllByIdIn(requestDto.movieIds());
            for(Movie movie : movieList){
                List<Actor> actorList = movie.getActors()!= null ? movie.getActors() : new ArrayList<>();
                actorList.add(actor);
                movie.setActors(actorList);
            }
            actor.setMovies(movieList);
        }
        return ActorResponseDto.fromEntity(actorRepository.save(actor));
    }

    @Override
    public ActorResponseDto updateActor(UUID actorID, UpdateActorRequestDto requestDto) throws ActorNotFoundException {
        Actor actor = actorRepository.findById(actorID).orElseThrow(() -> new ActorNotFoundException("Actor not found"));
        actor.setName(requestDto.name());
        actor.setAge(requestDto.age());
        actor.setActorImg(requestDto.actorImg());
        if(!requestDto.movieIds().isEmpty()){
            List<Movie> movieList = movieRepository.findAllByIdIn(requestDto.movieIds());
            for(Movie movie : movieList){
                List<Actor> actorList = movie.getActors()!= null ? movie.getActors() : new ArrayList<>();
                if(!actorList.contains(actor)){
                    actorList.add(actor);
                }
                movie.setActors(actorList);
            }
            for(Movie movie : actor.getMovies()){
                if(!movieList.contains(movie)){
                    movie.getActors().remove(actor);
                }
            }
            actor.setMovies(movieList);
        }else{
            List<Movie> movieList = actor.getMovies();
            for(Movie movie : movieList){
                movie.getActors().remove(actor);
            }
            actor.setMovies(new ArrayList<>());
        }
        return ActorResponseDto.fromEntity(actorRepository.save(actor));
    }

    @Override
    public ActorResponseDto getActorById(UUID actorID) throws ActorNotFoundException {
        Actor actor = actorRepository.findById(actorID).orElseThrow(() -> new ActorNotFoundException("Actor not found"));
        return ActorResponseDto.fromEntity(actor);
    }

    @Override
    public void deleteActor(UUID actorID) {
        Actor actor = actorRepository.findById(actorID).orElse(null);
        if(actor != null){
            for(Movie movie: actor.getMovies()){
                movie.getActors().remove(actor);
            }
        }
        actorRepository.deleteById(actorID);
    }
}
