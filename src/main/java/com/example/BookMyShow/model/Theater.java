package com.example.BookMyShow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "theaters")
public class Theater extends BaseModel{

    private String theaterName;
    private String theaterAddress;
    private String theaterImageUrl;
    @OneToMany(mappedBy = "theater", fetch = FetchType.EAGER)
    private List<Auditorium> auditoriums;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
