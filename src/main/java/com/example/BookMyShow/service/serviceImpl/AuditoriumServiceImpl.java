package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.AuditoriumResponseDto;
import com.example.BookMyShow.dto.CreateAuditoriumRequestDto;
import com.example.BookMyShow.dto.UpdateAuditoriumRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.TheaterNotFoundException;
import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.Theater;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;
import com.example.BookMyShow.repository.AuditoriumRepository;
import com.example.BookMyShow.repository.TheaterRepository;
import com.example.BookMyShow.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("AuditoriumServiceImpl")
public class AuditoriumServiceImpl implements AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final TheaterRepository theaterRepository;
    @Autowired
    public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository, TheaterRepository theaterRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public AuditoriumResponseDto addAuditorium(CreateAuditoriumRequestDto createAuditoriumDto) throws TheaterNotFoundException {
        Theater theater = theaterRepository.findById(createAuditoriumDto.theaterId()).orElseThrow(()-> new TheaterNotFoundException("Theater Not Found"));
        Auditorium auditorium = CreateAuditoriumRequestDto.toAuditorium(createAuditoriumDto);

        List<Auditorium> auditoriumList = theater.getAuditoriums() == null ? new ArrayList<>() : theater.getAuditoriums();
        auditoriumList.add(auditorium);
        theater.setAuditoriums(auditoriumList);
        auditorium.setTheater(theater);
        return AuditoriumResponseDto.fromAuditorium(auditoriumRepository.save(auditorium));
    }

    @Override
    public List<AuditoriumResponseDto> getAllAuditoriums() {
        return auditoriumRepository.findAll().stream().map(AuditoriumResponseDto::fromAuditorium).toList();
    }

    @Override
    public AuditoriumResponseDto getAuditoriumById(UUID auditoriumId) throws AuditoriumNotFoundException {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).orElseThrow(()-> new AuditoriumNotFoundException("Auditorium Not Found"));

        return AuditoriumResponseDto.fromAuditorium(auditorium);
    }

    @Override
    public AuditoriumResponseDto updateAuditorium(UUID auditoriumId, UpdateAuditoriumRequestDto updateAuditoriumRequestDto) throws TheaterNotFoundException, AuditoriumNotFoundException {
        Theater theater = theaterRepository.findById(updateAuditoriumRequestDto.theaterId()).orElseThrow(()-> new TheaterNotFoundException("Theater Not Found"));
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).orElseThrow(()-> new AuditoriumNotFoundException("Auditorium Not Found"));
        auditorium.setAuditoriumName(updateAuditoriumRequestDto.auditoriumName());
        auditorium.setTheater(theater);
        auditorium.setCapacity(updateAuditoriumRequestDto.capacity());
        List<AuditoriumFeatures> auditoriumFeaturesList = new ArrayList<>();
        for (String auditoriumFeature : updateAuditoriumRequestDto.auditoriumFeatures()) {
            auditoriumFeaturesList.add(AuditoriumFeatures.valueOf(auditoriumFeature));
        }
        auditorium.setAuditoriumFeatures(auditoriumFeaturesList);
        return AuditoriumResponseDto.fromAuditorium(auditoriumRepository.save(auditorium));
    }

    @Override
    public void deleteAuditorium(UUID auditoriumId) {
        auditoriumRepository.deleteById(auditoriumId);
    }
}
