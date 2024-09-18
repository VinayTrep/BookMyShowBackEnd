package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.ActorResponseDto;
import com.example.BookMyShow.dto.CreateActorRequestDto;
import com.example.BookMyShow.dto.UpdateActorRequestDto;
import com.example.BookMyShow.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/actor")
public class ActorController {


    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/")
    public ResponseEntity<ActorResponseDto> createActor(@RequestBody CreateActorRequestDto requestDto){
        ActorResponseDto actorResponseDto = actorService.createActor(requestDto);
        return ResponseEntity.ok(actorResponseDto);
    }

    @GetMapping("/{actorID}")
    public ResponseEntity<ActorResponseDto> getActor(@PathVariable UUID actorID){
        return ResponseEntity.ok(actorService.getActorById(actorID));
    }

    @PutMapping("/{actorID}")
    public ResponseEntity<ActorResponseDto> updateActor(@PathVariable UUID actorID, @RequestBody UpdateActorRequestDto requestDto){
        return ResponseEntity.ok(actorService.updateActor(actorID,requestDto));
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable UUID actorId){
        actorService.deleteActor(actorId);
        return ResponseEntity.ok("Actor deleted successfully");
    }
}
