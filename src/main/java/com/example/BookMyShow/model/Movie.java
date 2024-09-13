package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.MovieFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseModel{
    private String title;
    private String genre;
    private int year;
    private String director;
    private int duration;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<MovieFeatures> movieFeatures;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Actor> actors;
}
