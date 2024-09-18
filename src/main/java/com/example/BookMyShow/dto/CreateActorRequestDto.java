package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Actor;

import java.util.List;
import java.util.UUID;

public record CreateActorRequestDto(String name,int age,String actorImg,List<UUID> movieIds) {

    public static Actor fromDto(CreateActorRequestDto createActorRequestDto) {
        Actor actor = new Actor();
        actor.setName(createActorRequestDto.name);
        actor.setAge(createActorRequestDto.age);
        actor.setActorImg(createActorRequestDto.actorImg);
        return actor;
    }
}
