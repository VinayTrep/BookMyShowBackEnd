package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.AuditoriumResponseDto;
import com.example.BookMyShow.dto.CreateAuditoriumRequestDto;
import com.example.BookMyShow.dto.UpdateAuditoriumRequestDto;
import com.example.BookMyShow.exception.AuditoriumNameEmptyException;
import com.example.BookMyShow.exception.InvalidAuditoriumFeature;
import com.example.BookMyShow.exception.InvalidCapacityException;
import com.example.BookMyShow.model.constants.AuditoriumFeatures;
import com.example.BookMyShow.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auditorium")
public class AuditoriumController {

    public final AuditoriumService auditoriumService;

    @Autowired
    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @PostMapping("/")
    public ResponseEntity<AuditoriumResponseDto> addAuditorium(@RequestBody CreateAuditoriumRequestDto requestDto) {
        validateAuditoriumRequestDto(requestDto.auditoriumName(),requestDto.capacity(),requestDto.auditoriumFeatures());
        AuditoriumResponseDto responseDto = auditoriumService.addAuditorium(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{auditoriumId}")
    public ResponseEntity<AuditoriumResponseDto> getAuditoriumById(@PathVariable("auditoriumId") UUID auditoriumId) {
        return ResponseEntity.ok(auditoriumService.getAuditoriumById(auditoriumId));
    }

    @GetMapping("/")
    public ResponseEntity<List<AuditoriumResponseDto>> getAllAuditorium() {
        return ResponseEntity.ok(auditoriumService.getAllAuditoriums());
    }

    @PutMapping("/{auditoriumId}")
    public ResponseEntity<AuditoriumResponseDto> updateAuditorium(@PathVariable("auditoriumId") UUID auditoriumId, @RequestBody UpdateAuditoriumRequestDto requestDto){
        validateAuditoriumRequestDto(requestDto.auditoriumName(),requestDto.capacity(),requestDto.auditoriumFeatures());
        return ResponseEntity.ok(auditoriumService.updateAuditorium(auditoriumId, requestDto));
    }

    @DeleteMapping("/{auditoriumId}")
    public ResponseEntity<String> deleteAuditorium(@PathVariable("auditoriumId") UUID auditoriumId) {
        auditoriumService.deleteAuditorium(auditoriumId);
        return ResponseEntity.ok("Auditorium deleted Successfully");
    }

    private void validateAuditoriumRequestDto(String auditoriumName, int capacity, List<String> auditoriumFeatures) {
        if(auditoriumName == null || auditoriumName.isEmpty() || auditoriumName.isBlank()) {
            throw new AuditoriumNameEmptyException("Auditorium name cannot be empty");
        }

        if (capacity < 1){
            throw new InvalidCapacityException("Capacity cannot be less than 1");
        }

        try{
            for (String feature : auditoriumFeatures) {
                AuditoriumFeatures.valueOf(feature);
            }
        }catch (IllegalArgumentException e) {
            throw new InvalidAuditoriumFeature("Use proper Auditorium feature");
        }
    }
}
