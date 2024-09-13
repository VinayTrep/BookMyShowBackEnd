package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    Optional<Seat> findSeatByColumnNumberAndRowsNumberAndAuditoriumId(int columnNumber, int rowsNumber, UUID auditoriumId);
    Optional<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findAllByAuditoriumId(UUID auditoriumId);
}
