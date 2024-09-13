package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Show;

public record ShowResponseDto() {

    public static ShowResponseDto fromShow(Show show) {
        return new ShowResponseDto();
    }
}
