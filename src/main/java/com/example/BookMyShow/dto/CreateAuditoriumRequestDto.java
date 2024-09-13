package com.example.BookMyShow.dto;


import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record CreateAuditoriumRequestDto(String auditoriumName, int capacity, List<String> auditoriumFeatures, UUID theaterId) {

    public static Auditorium toAuditorium(CreateAuditoriumRequestDto createAuditoriumDto) {
        Auditorium auditorium = new Auditorium();
        auditorium.setAuditoriumName(createAuditoriumDto.auditoriumName);
        auditorium.setCapacity(createAuditoriumDto.capacity);
        List<AuditoriumFeatures> auditoriumFeaturesList = new ArrayList<>();
        for (String auditoriumFeature : createAuditoriumDto.auditoriumFeatures) {
            auditoriumFeaturesList.add(AuditoriumFeatures.valueOf(auditoriumFeature));
        }
        auditorium.setAuditoriumFeatures(auditoriumFeaturesList);
        auditorium.setShows(new ArrayList<>());
        auditorium.setSeats(new ArrayList<>());
        return auditorium;
    }
}
