package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.BMSShowResponseDto;
import com.example.BookMyShow.dto.BMSShowsResponseDto;
import com.example.BookMyShow.dto.CreateShowRequestDto;
import com.example.BookMyShow.dto.UpdateShowRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.MovieNotFoundException;
import com.example.BookMyShow.exception.ShowNotFoundException;
import com.example.BookMyShow.model.*;
import com.example.BookMyShow.repository.AuditoriumRepository;
import com.example.BookMyShow.repository.MovieRepository;
import com.example.BookMyShow.repository.ShowRepository;
import com.example.BookMyShow.service.ShowService;
import com.example.BookMyShow.service.Strategy.PriceGenerationFactory;
import com.example.BookMyShow.service.Strategy.PriceGenerationStrategy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;

    public ShowServiceImpl(ShowRepository showRepository, MovieRepository movieRepository, AuditoriumRepository auditoriumRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.auditoriumRepository = auditoriumRepository;
    }


    @Override
    public BMSShowResponseDto createShow(CreateShowRequestDto createShowRequestDto)
            throws AuditoriumNotFoundException, MovieNotFoundException {
        Auditorium auditorium = auditoriumRepository.findById(createShowRequestDto.auditoriumId())
                .orElseThrow(()-> new AuditoriumNotFoundException("Auditorium Not Found"));
        Movie movie = movieRepository.findById(createShowRequestDto.movieId())
                .orElseThrow(()-> new MovieNotFoundException("Movie Not Found"));
        Show show = CreateShowRequestDto.fromDto(createShowRequestDto);

        show.setMovie(movie);

        //set show in the auditorium and then set auditorium in the show
        List<Show> showList = auditorium.getShows() != null ? auditorium.getShows() : new ArrayList<Show>();
        showList.add(show);
        auditorium.setShows(showList);
        show.setAuditorium(auditorium);

        // generate and set showSeats
        List<ShowSeat> showSeatList = generateShowSeats(show,auditorium.getSeats());
        show.setShowSeats(showSeatList);
        return BMSShowResponseDto.fromShow(showRepository.save(show));
    }

    @Override
    public BMSShowResponseDto getShow(UUID showId) throws ShowNotFoundException {
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show Not Found"));
        return BMSShowResponseDto.fromShow(show);
    }

    @Override
    public List<BMSShowsResponseDto> getAllShow(UUID auditorId) throws ShowNotFoundException {
        Instant currentTime = Instant.now();
        return showRepository.findAllByAuditoriumIdAndStartTimeGreaterThan(auditorId,currentTime).stream()
                .map(BMSShowsResponseDto::fromShow)
                .collect(Collectors.toList());
    }


    @Override
    public BMSShowResponseDto updateShow(UpdateShowRequestDto updateShowRequestDto) {
        return null;
    }

    @Override
    public void deleteShow(UUID showId) {
        showRepository.deleteById(showId);
    }

    @Override
    public List<ShowSeat> generateShowSeats(Show show, List<Seat> showSeats) {
        PriceGenerationStrategy strategy = PriceGenerationFactory.getPriceGenerationStrategy(show.getStrategyType());
        return strategy.generatePrice(show,showSeats);
    }
}
