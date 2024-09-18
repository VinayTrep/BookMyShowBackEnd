package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
    List<Show> findAllByAuditoriumIdAndStartTimeGreaterThan( UUID auditoriumId,Instant startTime);
}
