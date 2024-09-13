package com.example.BookMyShow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Actors")
public class Actor extends BaseModel{
    private String name;
    private int age;
    private String actorImg;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Movie> movies;
}
