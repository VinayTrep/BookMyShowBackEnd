package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActorRepository extends JpaRepository<Actor, UUID> {

    List<Actor> findAllByIdIn(List<UUID> ids);
}
