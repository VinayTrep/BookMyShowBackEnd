package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Actor;

import java.io.Serializable;
import java.util.UUID;

public record MovieActorResponseDto(UUID actorId,String name,int age,String actorImg) implements Serializable {

    public static MovieActorResponseDto fromActor(Actor actor) {
        return new MovieActorResponseDto(actor.getId(),actor.getName(),actor.getAge(),actor.getActorImg());
    }
}
