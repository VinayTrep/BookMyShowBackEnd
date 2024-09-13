package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CreateSeatRequestDto;
import com.example.BookMyShow.dto.SeatResponseDto;
import com.example.BookMyShow.dto.UpdateSeatRequestDto;
import com.example.BookMyShow.exception.AuditoriumNotFoundException;
import com.example.BookMyShow.exception.SeatExistsException;
import com.example.BookMyShow.exception.SeatNotFoundException;
import com.example.BookMyShow.model.Auditorium;
import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.constants.SeatStatus;
import com.example.BookMyShow.model.constants.SeatType;
import com.example.BookMyShow.repository.AuditoriumRepository;
import com.example.BookMyShow.repository.SeatRepository;
import com.example.BookMyShow.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("SeatServiceImpl")
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final AuditoriumRepository auditoriumRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, AuditoriumRepository auditoriumRepository) {
        this.seatRepository = seatRepository;
        this.auditoriumRepository = auditoriumRepository;
    }
    //Create a seat if the auditorium is valid and also if the seat with same row and column does not exist
    @Override
    public SeatResponseDto addSeat(CreateSeatRequestDto requestDto) throws AuditoriumNotFoundException{
        //check if column already exists
        Auditorium auditorium = getAuditorium(requestDto.auditoriumId());
        //check if seat already exists
        Optional<Seat> seatOptional = seatRepository.findSeatByColumnNumberAndRowsNumberAndAuditoriumId(requestDto.columnNumber(),requestDto.rowsNumber() ,requestDto.auditoriumId());
        if(seatOptional.isPresent()){
            throw new SeatExistsException("Seat with given colum and row number already exists in the given Auditorium ID");
        }
        Seat seat = CreateSeatRequestDto.fromDto(requestDto);

        List<Seat> seats = auditorium.getSeats() == null ? new ArrayList<>() : auditorium.getSeats();
        seats.add(seat);
        auditorium.setSeats(seats);
        seat.setAuditorium(auditorium);
        return SeatResponseDto.fromSeat(seatRepository.save(seat));
    }
    //Create a seat if the auditorium is valid and also if the seat with same row and column does not exist
    @Override
    public List<SeatResponseDto> addSeats(List<CreateSeatRequestDto> requestDtos) throws AuditoriumNotFoundException{
        List<Seat> seats = new ArrayList<>();
        for (CreateSeatRequestDto requestDto : requestDtos) {
            Auditorium auditorium = getAuditorium(requestDto.auditoriumId());
            Optional<Seat> seatOptional = seatRepository.findSeatByColumnNumberAndRowsNumberAndAuditoriumId(requestDto.columnNumber(),requestDto.rowsNumber() ,requestDto.auditoriumId());
            if(seatOptional.isPresent()){
                throw new SeatExistsException("Seat with given colum and row number already exists in the given Auditorium ID");
            }
            Seat seat = CreateSeatRequestDto.fromDto(requestDto);
            List<Seat> audiSeats = auditorium.getSeats() == null ? new ArrayList<>() : auditorium.getSeats();
            audiSeats.add(seat);
            auditorium.setSeats(audiSeats);
            seat.setAuditorium(auditorium);
            seats.add(seat);
        }
        return seatRepository.saveAll(seats).stream().map(SeatResponseDto::fromSeat).toList();
    }

    @Override
    public SeatResponseDto getSeat(UUID seatId) throws SeatNotFoundException {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException("Invalid Seat ID"));
        return SeatResponseDto.fromSeat(seat);
    }

    @Override
    public SeatResponseDto getSeatBySeatNumber(String seatNumber) throws SeatNotFoundException {
        Seat seat = seatRepository.findBySeatNumber(seatNumber).orElseThrow(() -> new SeatNotFoundException("Invalid SeatNumber"));
        return SeatResponseDto.fromSeat(seat);
    }

    @Override
    public List<SeatResponseDto> getAllSeats(UUID auditoriumId) throws AuditoriumNotFoundException {
        return seatRepository.findAllByAuditoriumId(auditoriumId).stream().map(SeatResponseDto::fromSeat).toList();
    }

    @Override
    public SeatResponseDto updateSeat(UUID seatId, UpdateSeatRequestDto requestDto) throws AuditoriumNotFoundException, SeatNotFoundException {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException("Invalid Seat ID"));
        Auditorium auditorium = getAuditorium(requestDto.auditoriumId());

        seat.setSeatNumber(requestDto.SeatNumber());
        seat.setAuditorium(auditorium);
        seat.setSeatType(SeatType.valueOf(requestDto.SeatType()));
        seat.setSeatStatus(SeatStatus.valueOf(requestDto.seatStatus()));
        seat.setColumnNumber(requestDto.columnNumber());
        seat.setRowsNumber(requestDto.rowsNumber());
        return SeatResponseDto.fromSeat(seatRepository.save(seat));
    }


    @Override
    public void deleteSeat(UUID seatId) {
        seatRepository.deleteById(seatId);
    }

    private Auditorium getAuditorium(UUID auditoriumId){
        return auditoriumRepository.findById(auditoriumId).orElseThrow(() -> new AuditoriumNotFoundException("Invalid Auditorium ID"));
    }
}
