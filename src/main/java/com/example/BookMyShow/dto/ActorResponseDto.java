package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ActorResponseDto(UUID actorID,String name, int age, String actorImg, List<ActorMovieResponseDto> movies) {

    public static ActorResponseDto fromEntity(Actor actor) {
        return new ActorResponseDto(actor.getId(), actor.getName(), actor.getAge(), actor.getActorImg(),
                actor.getMovies()==null? new ArrayList<>():actor.getMovies().stream().map(ActorMovieResponseDto::fromEntity).collect(Collectors.toList()));
    }
}
