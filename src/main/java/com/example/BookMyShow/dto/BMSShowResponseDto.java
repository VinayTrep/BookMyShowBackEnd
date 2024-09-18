package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record BMSShowResponseDto(UUID showID,String startTime, String endTime, ShowMovieResponseDto movie,
                                 ShowAuditoriumResponseDto auditorium, List<ShowSeatResponseDto> showSeats) {


    public static BMSShowResponseDto fromShow(Show show) {
        List<ShowSeatResponseDto> showSeats = show.getShowSeats().stream().map(ShowSeatResponseDto::fromShowSeat).toList();
        return new BMSShowResponseDto(show.getId(),show.getStartTime().toString(),show.getEndTime().toString(),ShowMovieResponseDto.from(show.getMovie()),
                ShowAuditoriumResponseDto.fromAuditorium(show.getAuditorium()), showSeats);
    }
}
