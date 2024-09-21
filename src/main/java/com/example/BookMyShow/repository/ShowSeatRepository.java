package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, UUID> {
}
