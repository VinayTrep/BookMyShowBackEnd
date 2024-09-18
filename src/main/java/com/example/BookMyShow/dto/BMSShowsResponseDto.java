package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;

import java.time.Instant;
import java.util.UUID;

public record BMSShowsResponseDto(UUID showId , Instant startTime, Instant endTime, ShowMovieResponseDto movie, ShowAuditoriumResponseDto auditorium) {

    public static BMSShowsResponseDto fromShow(Show show) {
        return new BMSShowsResponseDto(show.getId(),show.getStartTime(),show.getEndTime(),ShowMovieResponseDto.from(show.getMovie()),
                ShowAuditoriumResponseDto.fromAuditorium(show.getAuditorium()));
    }
}
