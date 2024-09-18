package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.MovieFeatures;
import com.example.BookMyShow.model.constants.MovieGenre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseModel{
    private String title;
    private MovieGenre genre;
    private int year;
    private String director;
    private int duration;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<MovieFeatures> movieFeatures;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Actor> actors;
}
