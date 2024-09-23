package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.BMSShowResponseDto;
import com.example.BookMyShow.dto.BMSShowsResponseDto;
import com.example.BookMyShow.dto.CreateShowRequestDto;
import com.example.BookMyShow.dto.UpdateShowRequestDto;
import com.example.BookMyShow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/")
    public ResponseEntity<BMSShowResponseDto> createShow(@RequestBody CreateShowRequestDto requestDto) {
        BMSShowResponseDto responseDto = showService.createShow(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{showID}")
    public ResponseEntity<BMSShowResponseDto> getShow(@PathVariable UUID showID) {
        BMSShowResponseDto responseDto = showService.getShow(showID);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BMSShowsResponseDto>> getAllShows(@RequestParam UUID auditoriumID){
        return ResponseEntity.ok(showService.getAllShow(auditoriumID));
    }
    @PutMapping("/{showID}")
    public ResponseEntity<BMSShowResponseDto> updateShow(@RequestBody UpdateShowRequestDto requestDto, @PathVariable UUID showID) {
        return ResponseEntity.ok(showService.updateShow(showID, requestDto));
    }
    @DeleteMapping("/{showID}")
    public ResponseEntity<String> deleteShow(@PathVariable UUID showID) {
        showService.deleteShow(showID);
        return ResponseEntity.ok("Deleted Show ID: " + showID);
    }
}
