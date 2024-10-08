package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository  extends JpaRepository<City, UUID> {
}
