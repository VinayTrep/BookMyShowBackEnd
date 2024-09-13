package com.example.BookMyShow.dto;


import java.util.List;
import java.util.UUID;

public record UpdateAuditoriumRequestDto(String auditoriumName, int capacity, List<String> auditoriumFeatures, UUID theaterId) {

}
