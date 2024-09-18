package com.example.BookMyShow.dto;

import java.util.UUID;

public record UpdateShowRequestDto(String startTime, String endTime,String strategyType, UUID movieId, UUID auditoriumId) {

}
