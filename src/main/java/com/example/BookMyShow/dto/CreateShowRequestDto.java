package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.service.Strategy.PriceStrategyType;

import java.time.Instant;
import java.util.UUID;

public record CreateShowRequestDto(String startTime, String endTime,String strategyType,
                                   UUID movieId, UUID auditoriumId) {

    public static Show fromDto(CreateShowRequestDto dto) {
        Show show = new Show();
        show.setStartTime(Instant.parse(dto.startTime));
        show.setEndTime(Instant.parse(dto.endTime));
        show.setStrategyType(PriceStrategyType.valueOf(dto.strategyType));
        return show;
    }
}