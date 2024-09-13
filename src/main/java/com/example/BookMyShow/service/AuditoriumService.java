package com.example.BookMyShow.service;


import com.example.BookMyShow.dto.AuditoriumResponseDto;
import com.example.BookMyShow.dto.CreateAuditoriumRequestDto;
import com.example.BookMyShow.dto.UpdateAuditoriumRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.TheaterNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AuditoriumService {

    public AuditoriumResponseDto addAuditorium(CreateAuditoriumRequestDto createAuditoriumDto) throws TheaterNotFoundException;
    public List<AuditoriumResponseDto> getAllAuditoriums();
    public AuditoriumResponseDto getAuditoriumById(UUID auditoriumId) throws AuditoriumNotFoundException;
    public AuditoriumResponseDto updateAuditorium(UUID auditoriumId, UpdateAuditoriumRequestDto updateAuditoriumRequestDto) throws TheaterNotFoundException, AuditoriumNotFoundException;
    public void deleteAuditorium(UUID auditoriumId);

}
