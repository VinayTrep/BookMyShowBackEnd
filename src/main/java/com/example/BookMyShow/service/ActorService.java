package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.ActorResponseDto;
import com.example.BookMyShow.dto.CreateActorRequestDto;
import com.example.BookMyShow.dto.UpdateActorRequestDto;
import com.example.BookMyShow.exception.ActorNotFoundException;

import java.util.UUID;

public interface ActorService {

    ActorResponseDto createActor(CreateActorRequestDto requestDto);
    ActorResponseDto updateActor(UUID actorID,UpdateActorRequestDto requestDto) throws ActorNotFoundException;
    ActorResponseDto getActorById(UUID actorID) throws ActorNotFoundException;
    void deleteActor(UUID actorID);
}
