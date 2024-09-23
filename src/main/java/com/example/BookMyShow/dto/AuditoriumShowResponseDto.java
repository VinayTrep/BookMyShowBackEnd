package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;

import java.io.Serializable;
import java.util.UUID;

public record AuditoriumShowResponseDto(UUID showID, String startTime,
                                        String endTime,
                                        ShowMovieResponseDto movie) implements Serializable {


    public static AuditoriumShowResponseDto fromShow(Show show) {
        return new AuditoriumShowResponseDto(show.getId(),show.getStartTime().toString(),
                show.getEndTime().toString(),
                ShowMovieResponseDto.from(show.getMovie()));
    }
}
