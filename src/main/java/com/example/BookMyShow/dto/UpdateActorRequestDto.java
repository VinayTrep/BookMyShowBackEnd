package com.example.BookMyShow.dto;

import java.util.List;
import java.util.UUID;

public record UpdateActorRequestDto(String name, int age, String actorImg, List<UUID> movieIds) {
}
