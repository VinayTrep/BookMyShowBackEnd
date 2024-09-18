package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.ShowSeat;
import com.example.BookMyShow.model.constants.ShowSeatStatus;

import java.util.UUID;

public record ShowSeatResponseDto(UUID showSeatID, int price, ShowSeatStatus showSeatStatus) {

    public static ShowSeatResponseDto fromShowSeat(ShowSeat showSeat) {
        return new ShowSeatResponseDto(showSeat.getId(),showSeat.getPrice(), showSeat.getShowSeatStatus());
    }
}
