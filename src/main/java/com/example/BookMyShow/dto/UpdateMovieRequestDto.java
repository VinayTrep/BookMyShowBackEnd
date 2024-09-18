package com.example.BookMyShow.dto;

import java.util.List;
import java.util.UUID;

public record UpdateMovieRequestDto(String title, String genre, int year,
                                    String director, int duration,
                                    List<String> movieFeatures,
                                    List<UUID> actorIds) {

}
