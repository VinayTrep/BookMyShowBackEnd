package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Theater;

import java.util.UUID;

public record UpdateTheaterRequestDto(String theaterName,
                                      String theaterAddress,
                                      String theaterImageUrl,
                                      UUID cityId) {

}
