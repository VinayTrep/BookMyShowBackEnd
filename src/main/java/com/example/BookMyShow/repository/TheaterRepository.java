package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TheaterRepository extends JpaRepository<Theater, UUID> {
}
